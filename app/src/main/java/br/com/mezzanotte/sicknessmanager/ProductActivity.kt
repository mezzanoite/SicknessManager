package br.com.mezzanotte.sicknessmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.Product
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btRegisterProduct.setOnClickListener {
            val productDao = DatabaseManager.getProductDao()
            productDao.insert(Product(
                    null,
                    etProduct.text.toString(),
                    etBrand.text.toString()
            ))
            finish()
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
}
