package br.com.mezzanotte.sicknessmanager

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.com.mezzanotte.sicknessmanager.fragments.AboutFragment
import br.com.mezzanotte.sicknessmanager.fragments.DiagnosisFragment
import br.com.mezzanotte.sicknessmanager.fragments.MapFragment
import br.com.mezzanotte.sicknessmanager.fragments.ConsumptionFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_register -> {
                toolbar.title = getString(R.string.title_register)
                val fragment = ConsumptionFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                toolbar.title = getString(R.string.title_map)
                val fragment = MapFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diagnosis -> {
                toolbar.title = getString(R.string.title_diagnosis)
                val fragment = DiagnosisFragment()
                fragment.addYourself(supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                toolbar.title = getString(R.string.title_about)
                val fragment = AboutFragment()
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
        toolbar.title = "Consumptions"
        val fragment = ConsumptionFragment()
        fragment.addYourself(supportFragmentManager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val firebaseAuth = FirebaseAuth.getInstance()
        when (item.itemId) {
            R.id.menuLogout ->
                    firebaseAuth.signOut()
        }
        return true
    }


}
