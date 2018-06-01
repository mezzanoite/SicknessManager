package br.com.mezzanotte.sicknessmanager.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.Product


class ProductViewModel : ViewModel() {

    private lateinit var currentList: LiveData<List<Product>>

    init {
        currentList = DatabaseManager.getProductDao().findAll()
    }

    fun getAllRegisters(): LiveData<List<Product>> {
        return currentList
    }

}

