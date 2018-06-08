package br.com.mezzanotte.sicknessmanager

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.mezzanotte.sicknessmanager.fragments.AboutFragment
import br.com.mezzanotte.sicknessmanager.fragments.ConsumptionFragment
import br.com.mezzanotte.sicknessmanager.fragments.DiagnosisFragment
import br.com.mezzanotte.sicknessmanager.fragments.MapFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


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
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.title_register)
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
            R.id.menuLogout -> {
                firebaseAuth.signOut()
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.menuExit -> {
                this.exitApp()
            }
        }
        return true
    }

    override fun onBackPressed() {
        this.exitApp()
    }

    private fun exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(1)
    }


}
