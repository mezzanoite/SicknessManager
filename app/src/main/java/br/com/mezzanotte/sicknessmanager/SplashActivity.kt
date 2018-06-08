package br.com.mezzanotte.sicknessmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        carregar_splash()
        firebaseAuth = FirebaseAuth.getInstance()

    }

    private fun carregar_splash() {

        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        splash.clearAnimation()
        splash.startAnimation(animacao)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}
