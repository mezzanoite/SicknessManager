package br.com.mezzanotte.sicknessmanager

import android.content.Context
import android.content.DialogInterface
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
                Toast.makeText(this, getString(R.string.welcome_back) + " ${firebaseUser.email}", Toast.LENGTH_LONG).show()
                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }

        btLogin.setOnClickListener{
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val message = validateEmailPasswordIsBlank(email, pass)
            if (message != null) {
                showAlertDialog(message, false)
            } else {
                firebaseAuth.signInWithEmailAndPassword(email, pass).
                        addOnCompleteListener(this, { task ->
                            if(task.isSuccessful) {
                                val editor = sharedPref.edit()
                                editor.putBoolean(AppConstants.SHARED_PREFS_KEEP_LOGIN, swKeep.isChecked)
                                editor.apply()
                                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(mainIntent)
                                finish()
                            } else {
                                Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                                //showMessage(view,"Error: ${task.exception?.message}")
                            }
                        })
            }
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

        builder.setView(dialogView)
                .setPositiveButton("OK", { dialog, _ ->
                    val email = dialogView.findViewById<EditText>(R.id.dgEmail).text.toString()
                    val password = dialogView.findViewById<EditText>(R.id.dgPassword).text.toString()

                    val message = validateEmailPasswordIsBlank(email, password)
                    if (message != null) {
                        dialog.dismiss()
                        showAlertDialog(message, true)
                    } else {
                        createUser(email, password, dialog)
                    }
                })
                .setNegativeButton(getString(R.string.cancel), { dialog, _ ->
                    dialog.dismiss()
                })
        builder.create().show()

    }

    private fun createUser(email: String, password: String, dialog: DialogInterface) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            task ->
                if (!task.isSuccessful) {
                    dialog.dismiss()
                    Toast.makeText(this, "Faillure on account creation: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun validateEmailPasswordIsBlank(email: String, password: String) : String? {
        var message: String? = null
        if (email.isBlank()) {
            message = getString(R.string.fields_required_email)
        }
        if (password.isBlank()) {
            if (message != null) {
                message += " " + getString(R.string.field_password)
            } else {
                message = getString(R.string.fields_required_password)
            }
        }
        return message
    }

    private fun showAlertDialog(text: String, onOkShowCreateDialog: Boolean) {
        val alertDialog = AlertDialog.Builder(this@LoginActivity).create()
        alertDialog.setTitle(getString(R.string.ups))
        alertDialog.setMessage(text)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            dialog, _ ->
            dialog.dismiss()
            if (onOkShowCreateDialog) {
                openCreateUserDialog()
            }
        })
        alertDialog.show()
    }
}
