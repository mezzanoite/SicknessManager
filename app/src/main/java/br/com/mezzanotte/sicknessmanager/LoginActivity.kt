package br.com.mezzanotte.sicknessmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        val keepLogin = sharedPref.getBoolean(AppConstants.SHARED_PREFS_KEEP_LOGIN, false)

        title = getString(R.string.loginTitle)

        clLogin.setBackgroundColor(resources.getColor(R.color.primary_light))
        firebaseAuth = FirebaseAuth.getInstance()

        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser != null) {
            if (!keepLogin) {
                firebaseAuth.signOut()
            } else {
                // Usuário se manteve logado
                Toast.makeText(this, "Welcome back ${firebaseUser.email}", Toast.LENGTH_LONG).show()
                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(mainIntent)
            }
        }

        btLogin.setOnClickListener{
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            firebaseAuth.signInWithEmailAndPassword(email, pass).
                    addOnCompleteListener(this, { task ->
                        if(task.isSuccessful) {
                            val editor = sharedPref.edit()
                            editor.putBoolean(AppConstants.SHARED_PREFS_KEEP_LOGIN, swKeep.isChecked)
                            editor.apply()
                            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(mainIntent)

                        } else {
                            Toast.makeText(this, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                            //showMessage(view,"Error: ${task.exception?.message}")
                        }
                    })

        }

        tvCreateAccount.setTextColor(resources.getColor(R.color.primary_dark))
        tvCreateAccount.setOnClickListener {
            openCreateUserDialog()
        }
    }

    private fun openCreateUserDialog() {

        val builder = AlertDialog.Builder(this)
        // Get the layout inflater
        val inflater = this.layoutInflater

        val dialogView = inflater.inflate(R.layout.dialog_signin, null)
        val email = dialogView.findViewById<EditText>(R.id.dgEmail)
        val password = dialogView.findViewById<EditText>(R.id.dgPassword)

        builder.setView(dialogView)
                .setPositiveButton("OK", { dialog, _ ->
                    createUser(email.text.toString(), password.text.toString())
                })
                .setNegativeButton("Cancel", { dialog, _ ->
                    dialog.dismiss()
                })
        builder.create().show()

    }

    private fun createUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
    }
}
