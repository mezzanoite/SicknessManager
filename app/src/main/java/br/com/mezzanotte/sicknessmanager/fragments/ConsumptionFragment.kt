package br.com.mezzanotte.sicknessmanager.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.mezzanotte.sicknessmanager.R
import br.com.mezzanotte.sicknessmanager.RegisterActivity
import br.com.mezzanotte.sicknessmanager.adapter.SicknessRegisterAdapter
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import br.com.mezzanotte.sicknessmanager.model.Product
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister
import br.com.mezzanotte.sicknessmanager.viewmodel.SicknessRegisterViewModel


class ConsumptionFragment : BaseFragment() {

    private var mAdapter: SicknessRegisterAdapter? = null

    private lateinit var mainViewModel: SicknessRegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_consumption, container, false)

        // Recuperando o meu botão flutuante de adicionar registros
        val fab: FloatingActionButton = view.findViewById(R.id.fabAddRegister)
        fab.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        val rvSicknessRegisters: RecyclerView = view.findViewById(R.id.rvSicknessRegisters)
        rvSicknessRegisters.adapter = mAdapter
        rvSicknessRegisters.layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?

        mainViewModel = ViewModelProviders.of(this).get(SicknessRegisterViewModel::class.java)
        mainViewModel.getAllRegisters().observe(this, Observer<List<SicknessRegister>> {
            registerList ->
            mAdapter = SicknessRegisterAdapter(
                    this.context!!,
                    registerList,
                    {

                        Toast.makeText(this.context, "Clicou sobre o item " + it.productId, Toast.LENGTH_LONG).show()
                    },
                    { sicknessRegister: SicknessRegister, menuView: View ->
                        val popUpMenu = PopupMenu(this.context!!, menuView)
                        popUpMenu.inflate(R.menu.item_menu)
                        popUpMenu.setOnMenuItemClickListener({ item: MenuItem? ->
                            when (item!!.itemId) {
                                R.id.share -> {
                                    share(sicknessRegister)
                                }
                                R.id.delete -> {
                                    val index = registerList?.indexOf(sicknessRegister)!!
                                    mAdapter?.notifyItemRemoved(index)
                                    if (index > 0) {
                                        rvSicknessRegisters.scrollToPosition(index - 1)
                                    }
                                    DatabaseManager.getSicknessRegisterDao().delete(sicknessRegister)

                                }
                            }
                            true
                        })

                        popUpMenu.show()
                    })
            rvSicknessRegisters.adapter = mAdapter
        })

        return view
    }

    fun share(sicknessRegister: SicknessRegister) {

        val product: Product? = DatabaseManager.getProductDao().findById(sicknessRegister.productId)
        if (product != null) {
            var status = ""
            when (sicknessRegister.statusImageId) {
                R.drawable.happy -> {
                    status = getString(R.string.statusHappy)
                }
                R.drawable.confused -> {
                    status = getString(R.string.statusConfused)
                }
                R.drawable.sad -> {
                    status = getString(R.string.statusSad)
                }
            }

            val myShareText = "Consumi o produto ${product.name} da marca ${product.brand} na data " +
                    "${sicknessRegister.dataConsumo} e fez eu me sentir: ${status}"
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.`package` = "com.whatsapp"
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, myShareText)
            try {
                activity?.startActivity(whatsappIntent)
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this.context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this.context, "Erro ao compartilhar o produto", Toast.LENGTH_SHORT).show()
        }
    }

}
