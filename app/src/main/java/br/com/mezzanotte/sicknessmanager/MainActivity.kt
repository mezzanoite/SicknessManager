package br.com.mezzanotte.sicknessmanager

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.mezzanotte.sicknessmanager.fragments.DiagnosisFragment
import br.com.mezzanotte.sicknessmanager.fragments.MapFragment
import br.com.mezzanotte.sicknessmanager.fragments.ConsumptionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_register -> {
                setTitle("Consumptions")
                val fragment = ConsumptionFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                setTitle("Gluten-free places")
                val fragment = MapFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diagnosis -> {
                setTitle("Your diagnosis")
                val fragment = DiagnosisFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        title = "Consumptions"
        val fragment = ConsumptionFragment()
        fragment.addYourself(supportFragmentManager)
    }

}
