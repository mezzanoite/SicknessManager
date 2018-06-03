package br.com.mezzanotte.sicknessmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.Product
import kotlinx.android.synthetic.main.activity_product.*
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AlertDialog


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "New product registration"

        btRegisterProduct.setOnClickListener {
            var message: String? = null
            val nomeProduto = etProduct.text.toString()
            val marca = etBrand.text.toString()
            if (nomeProduto.isBlank()) {
                message = "The following fields are required: Product name"
            }
            if (marca.isBlank()) {
                if (message != null) {
                    message += " and brand"
                } else {
                    message = "The following fields are required: Brand"
                }
            }

            if (message != null) {
                showAlertDialog(message)
            } else {
                val productDao = DatabaseManager.getProductDao()
                val id: Long = productDao.insert(Product(
                        null,
                        etProduct.text.toString(),
                        etBrand.text.toString()
                ))
                val resultIntent = Intent()
                resultIntent.putExtra(AppConstants.PRODUCT_ID, id)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }

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

    private fun showAlertDialog(text: String) {
        val alertDialog = AlertDialog.Builder(this@ProductActivity).create()
        alertDialog.setTitle("Ups!")
        alertDialog.setMessage(text)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            dialog, _ ->
            dialog.dismiss()
        })
        alertDialog.show()
    }
}
