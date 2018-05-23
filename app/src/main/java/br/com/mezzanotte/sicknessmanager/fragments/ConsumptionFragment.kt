package br.com.mezzanotte.sicknessmanager.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.mezzanotte.sicknessmanager.R
import br.com.mezzanotte.sicknessmanager.adapter.SicknessRegisterAdapter
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister
import android.content.Intent
import br.com.mezzanotte.sicknessmanager.RegisterActivity
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager


class ConsumptionFragment : BaseFragment() {

    private var adapter: SicknessRegisterAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_consumption, container, false)

        // Recuperando o meu botão flutuante de adicionar registros
        val fab: FloatingActionButton = view.findViewById<FloatingActionButton>(R.id.fabAddRegister)
        fab.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        adapter = SicknessRegisterAdapter(getSicknessRegisters(),{
            Toast.makeText(this.context, "Clicou sobre o item " + it.produto, Toast.LENGTH_LONG).show()
        })

        val rvSicknessRegisters: RecyclerView = view.findViewById<RecyclerView>(R.id.rvSicknessRegisters)
        rvSicknessRegisters.adapter = adapter
        rvSicknessRegisters.layoutManager = LinearLayoutManager(this.context)
        return view
    }

    fun getSicknessRegisters(): List<SicknessRegister> {

        val dao = DatabaseManager.getSicknessRegisterDAO()
        return dao.findAll()

        /*return listOf(
                SicknessRegister(null,"Batatinha", "Yoki" ,"17/05/2018" , ":)"),
                SicknessRegister(null,"Doritos", "Elma Chips" ,"18/05/2018" , ":("),
                SicknessRegister(null,"Mousse", "Chocolicias" ,"12/05/2018" , ":D"),
                SicknessRegister(null,"Sorvete", "Yopa" ,"10/05/2018" , ":'("),
                SicknessRegister(null,"Doritos", "Elma Chips" ,"09/05/2018" , ":|")

        )*/
    }



}
