package br.com.mezzanotte.sicknessmanager

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.adapter_sickness.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    companion object {

        val timePattern: String = "HH:mm"
        val datePattern: String = "dd/MM/yyyy"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cal = Calendar.getInstance()
        tvHour.text = SimpleDateFormat(RegisterActivity.timePattern).format(cal.time)
        tvDate.text = SimpleDateFormat(RegisterActivity.datePattern).format(cal.time)

        this.getTime(tvHour, this)
        this.getDate(tvDate, this)

        btRegister.setOnClickListener {
            val dao = DatabaseManager.getSicknessRegisterDAO()
            dao.insert(SicknessRegister(
                    null,
                    etProduto.text.toString(),
                    etMarca.text.toString() ,
                    tvDate.text.toString() + tvHour.text.toString() ,
                    ":(")
            )
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun getTime(textView: TextView, context: Context){

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

    fun getDate(textView: TextView, context: Context){

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
