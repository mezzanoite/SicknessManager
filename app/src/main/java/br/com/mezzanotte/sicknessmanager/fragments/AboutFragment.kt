package br.com.mezzanotte.sicknessmanager.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mezzanotte.sicknessmanager.R
import kotlinx.android.synthetic.main.fragment_about.*
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import android.R.attr.dial
import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat




class AboutFragment : BaseFragment() {

    companion object {
        const val MAKE_CALL_PERMISSION_REQUEST_CODE = 1
    }

    lateinit var mIvCall: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)

        requestCallPermission()

        mIvCall = view.findViewById<ImageView>(R.id.ivCall)
        mIvCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:+5511123456789")
            startActivity(callIntent)
        }

        return view
    }

    private fun requestCallPermission() {
        val permission = Manifest.permission.CALL_PHONE
        val grant = ContextCompat.checkSelfPermission(this.context!!, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permissionList = arrayOfNulls<String>(1)
            permissionList[0] = permission
            ActivityCompat.requestPermissions(activity!!, permissionList, MAKE_CALL_PERMISSION_REQUEST_CODE)
        }
    }

}
