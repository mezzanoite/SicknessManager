package br.com.mezzanotte.sicknessmanager.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister


class SicknessRegisterViewModel : ViewModel() {

    private lateinit var currentList: LiveData<List<SicknessRegister>>

    init {
        currentList = DatabaseManager.getSicknessRegisterDao().findAllLiveData()
    }

    fun getAllRegisters(): LiveData<List<SicknessRegister>> {
        return currentList
    }

}

