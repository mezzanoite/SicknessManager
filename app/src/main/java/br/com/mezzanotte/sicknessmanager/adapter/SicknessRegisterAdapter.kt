package br.com.mezzanotte.sicknessmanager.adapter

import android.content.Context
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.mezzanotte.sicknessmanager.R
import br.com.mezzanotte.sicknessmanager.model.SicknessRegister
import kotlinx.android.synthetic.main.adapter_sickness.view.*

class SicknessRegisterAdapter(
        val sicknessRegisters: List<SicknessRegister>,
        val onClick: (SicknessRegister) -> Unit) : RecyclerView.Adapter<SicknessRegisterAdapter.SicknessRegisterViewHolder>() {



    //  ViewHolder com as minhas views
    class SicknessRegisterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(sicknessRegister: SicknessRegister, onClick: (SicknessRegister) -> Unit) = with(itemView) {
            tvProduto.text = sicknessRegister.produto
            tvMarca.text = sicknessRegister.marca
            tvDataConsumo.text = sicknessRegister.dataConsumo
            tvStatus.text = sicknessRegister.status

            setOnClickListener { onClick(sicknessRegister) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SicknessRegisterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_sickness, parent, false)
        return SicknessRegisterViewHolder(itemView)
    }

    override fun getItemCount() = this.sicknessRegisters.count()

    override fun onBindViewHolder(holder: SicknessRegisterViewHolder, position: Int) {
        val sicknessRegister = sicknessRegisters[position]
        holder?.let {
            holder.bindView(sicknessRegister, onClick)
        }
    }

}