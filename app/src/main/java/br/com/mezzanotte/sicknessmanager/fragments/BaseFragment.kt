package br.com.mezzanotte.sicknessmanager.fragments


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.mezzanotte.sicknessmanager.R


/**
 * Fragment base com coisas comuns entre todos os fragments
 */
open class BaseFragment : Fragment() {

    fun addYourself(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, this, this.javaClass.simpleName)
                .addToBackStack(this.javaClass.simpleName)
                .commit()
    }
}
