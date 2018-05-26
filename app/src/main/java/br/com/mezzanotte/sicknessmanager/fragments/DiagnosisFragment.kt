package br.com.mezzanotte.sicknessmanager.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.mezzanotte.sicknessmanager.R
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister

class DiagnosisFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        /*val dao = DatabaseManager.getSicknessRegisterDAO()
        dao.insert(SicknessRegister(null,"Fandangos", "Elma Chips" ,"17/05/2018" , ":("))
        dao.insert(SicknessRegister(null,"Água", "Bonafont" ,"18/05/2018" , ":D"))
        dao.insert(SicknessRegister(null,"Leite", "Piracanjuba" ,"12/05/2018" , ":/"))
        dao.insert(SicknessRegister(null,"Sorvete", "Yopa" ,"10/05/2018" , ":'("))*/
        return inflater.inflate(R.layout.fragment_diagnosis, container, false)
    }


}
