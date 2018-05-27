package br.com.mezzanotte.sicknessmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AnimationUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
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

    override fun onStart() {
        super.onStart()

        /*firebaseAuth.signInWithEmailAndPassword("teste@sicknessmanager.com", "teste10")
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.w("TAG", "Logou nessa caralha")
                        val user = firebaseAuth.getCurrentUser()
                        if (user != null) {
                            Log.w("TAG", user.email)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "Falhou login nessa caralha")
                    }

                })*/

    }


}
