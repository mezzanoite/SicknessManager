package br.com.mezzanotte.sicknessmanager.fragments


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import br.com.mezzanotte.sicknessmanager.R
import br.com.mezzanotte.sicknessmanager.database.DatabaseManager
import kotlinx.android.synthetic.main.fragment_diagnosis.*

class DiagnosisFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_diagnosis, container, false)

        val sicknessRegisters = DatabaseManager.getSicknessRegisterDao().findAll()

        val mLLNoDiagnosis = view.findViewById<LinearLayout>(R.id.llNoDiagnosis)
        val mClDiagnosis = view.findViewById<ConstraintLayout>(R.id.clDiagnosis)

        if (sicknessRegisters.isNotEmpty()) {
            mLLNoDiagnosis.visibility = LinearLayout.GONE
            mClDiagnosis.visibility = ConstraintLayout.VISIBLE

            val productCount = DatabaseManager.getSicknessRegisterDao().findMostOcurrenceOfStatus(R.drawable.sad)
            val product = DatabaseManager.getProductDao().findById(productCount.productId)

            val mTvProductMostBad = view.findViewById<TextView>(R.id.tvProductMostBad)
            val mTvBrandMostBad = view.findViewById<TextView>(R.id.tvBrandMostBad)
            val mTtvProductMostBadCount = view.findViewById<TextView>(R.id.tvProductMostBadCount)
            mTvProductMostBad.text = product!!.name
            mTvBrandMostBad.text = product!!.brand
            mTtvProductMostBadCount.text = productCount.ocurrences.toString()

        } else {
            mLLNoDiagnosis.visibility = LinearLayout.VISIBLE
            mClDiagnosis.visibility = ConstraintLayout.GONE
        }

    return view
}

override fun onStart() {
    super.onStart()
    //val productCount = DatabaseManager.getSicknessRegisterDao().findMostOcurrenceOfStatus(R.drawable.happy)
    //Log.i("TAG", "${productCount.productId} | ${productCount.ocurrences}")
}


}
