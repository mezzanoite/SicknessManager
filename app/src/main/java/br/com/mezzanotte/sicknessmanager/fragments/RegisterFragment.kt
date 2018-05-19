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
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private var adapter: SicknessRegisterAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)

        // Recuperando o meu botão flutuante de adicionar registros
        val fab: FloatingActionButton = view.findViewById<FloatingActionButton>(R.id.fabAddRegister)
        fab.setOnClickListener {
            Toast.makeText(this.context, "Apertei o botaum flutuante", Toast.LENGTH_LONG).show()
            // TODO abrir tela para adicionar novo registro
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

        return listOf(
                SicknessRegister("Batatinha", "Yoki" ,"17/05/2018" , ":)"),
                SicknessRegister("Doritos", "Elma Chips" ,"18/05/2018" , ":(")
        )
    }

}
