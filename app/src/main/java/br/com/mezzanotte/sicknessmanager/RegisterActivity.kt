package br.com.mezzanotte.sicknessmanager

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.Product
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister
import br.com.mezzanotte.sicknessmanager.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity() {

    var productId: Long = 0L
    var statusImageId: Int = R.drawable.happy
    var sicknessRegisterEdit: SicknessRegister? = null

    lateinit var productViewModel: ProductViewModel

    companion object {
        const val FAB_REQUEST_CODE = 0
        const val timePattern: String = "HH:mm"
        const val datePattern: String = "dd/MM/yyyy"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sicknessRegisterEdit = intent.getParcelableExtra(AppConstants.REGISTER_ITEM)

        if (sicknessRegisterEdit != null) {
            title = getString(R.string.edit_register)
            refreshProduct(sicknessRegisterEdit!!.productId)
            val dataString = sicknessRegisterEdit!!.dataConsumo
            val dataStringSplitted = dataString.split(" ")
            tvDate.text = dataStringSplitted[0]
            tvHour.text = dataStringSplitted[1]
            when (sicknessRegisterEdit!!.statusImageId) {
                R.drawable.happy -> {
                    setImageStatus(ivHappy, R.drawable.happy)
                }
                R.drawable.confused -> {
                    setImageStatus(ivConfused, R.drawable.confused)
                }
                R.drawable.sad -> {
                    setImageStatus(ivSad, R.drawable.sad)
                }
            }
        } else {
            title = getString(R.string.new_register_text)
            val cal = Calendar.getInstance()
            tvHour.text = SimpleDateFormat(RegisterActivity.timePattern).format(cal.time)
            tvDate.text = SimpleDateFormat(RegisterActivity.datePattern).format(cal.time)
        }

        this.getTime(tvHour, this)
        this.getDate(tvDate, this)

        // Criando observer para a lista de produtos cadastrados
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.getAllRegisters().observe(this, Observer {

            // Os produtos serão mostrados em uma lista do text view de autocomplete
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, it)
            etProducts.setAdapter(arrayAdapter)
            // Listener para abrir o menu de opções ao clicar no text view
            etProducts.setOnClickListener{
                etProducts.showDropDown()
            }
            // Listener para quando um item for escolhido
            etProducts.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
                val product: Product = parent!!.getItemAtPosition(position) as Product
                productId = product.productId!!
            }
        })

        fabAddProduct.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivityForResult(intent, FAB_REQUEST_CODE)
        }

        ivHappy.setOnClickListener {
            setImageStatus(ivHappy, R.drawable.happy)
        }

        ivConfused.setOnClickListener {
            setImageStatus(ivConfused, R.drawable.confused)
        }

        ivSad.setOnClickListener {
            setImageStatus(ivSad, R.drawable.sad)
        }

        btRegister.setOnClickListener {
            if (productId == null || productId == 0L) {
                showAlertDialog()
            } else {
                val dao = DatabaseManager.getSicknessRegisterDao()
                val dataConsumo = tvDate.text.toString() + " " +  tvHour.text.toString()
                if (sicknessRegisterEdit != null) {
                    sicknessRegisterEdit!!.dataConsumo = dataConsumo
                    sicknessRegisterEdit!!.statusImageId = statusImageId
                    sicknessRegisterEdit!!.productId = productId
                    dao.update(sicknessRegisterEdit!!)
                } else {
                    dao.insert(SicknessRegister(null, dataConsumo, statusImageId, productId))
                }
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            FAB_REQUEST_CODE ->
                if (resultCode == Activity.RESULT_OK) {
                    productId = data?.getLongExtra(AppConstants.PRODUCT_ID, 0L)!!
                }
        }
        if (productId != 0L) {
            refreshProduct(productId)
        }
    }

    // Tratamento quando clicka no botão voltar da ActionBar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this@RegisterActivity).create()
        alertDialog.setTitle(getString(R.string.ups))
        alertDialog.setMessage(getString(R.string.forgot_product))
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            dialog, _ ->
            dialog.dismiss()
        })
        alertDialog.show()
    }

    private fun refreshProduct(id: Long) {
        this.productId = id
        etProducts.setText(DatabaseManager.getProductDao().findById(id).toString())
    }

    private fun setImageStatus(imageView: ImageView, drawable: Int) {
        // Primeiro desabilito todos os status
        ivHappy.setImageResource(R.drawable.happy_off)
        ivConfused.setImageResource(R.drawable.confused_off)
        ivSad.setImageResource(R.drawable.sad_off)

        // Seto o id e a imagem que estará ativada
        statusImageId = drawable
        imageView.setImageResource(drawable)
    }

    private fun getTime(textView: TextView, context: Context){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            textView.text = SimpleDateFormat(RegisterActivity.timePattern).format(cal.time)
        }

        btHour.setOnClickListener {
            TimePickerDialog(context, timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true).show()
        }
    }

    private fun getDate(textView: TextView, context: Context){

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            textView.text = SimpleDateFormat(RegisterActivity.datePattern).format(cal.time)

        }

        btDate.setOnClickListener {
            DatePickerDialog(context, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

}
