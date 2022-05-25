package com.dimdimbjg.ufa.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.dimdimbjg.ufa.databinding.ActivityLoginBinding
import com.dimdimbjg.ufa.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    private val activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activityLoginBinding.root)

        mAuth = FirebaseAuth.getInstance()

        activityLoginBinding.btLogin.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin() {
        val email: String = activityLoginBinding.editEmail.text.toString().trim()
        val password: String = activityLoginBinding.editPassword.text.toString().trim()

        if (email.isEmpty()) {
            activityLoginBinding.editEmail.error = "Harap isi email dengan benar"
            activityLoginBinding.editEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            activityLoginBinding.editEmail.error = "Harap isi email dengan benar"
            activityLoginBinding.editEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            activityLoginBinding.editPassword.error = "Harap isi password anda"
            activityLoginBinding.editPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            activityLoginBinding.editPassword.error = "Minimal 6 huruf"
            activityLoginBinding.editPassword.requestFocus()
            return
        }

        activityLoginBinding.progressBar.isVisible

        mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                //redirect
                activityLoginBinding.progressBar.isGone
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Gagal login, silahkan cek kembali username dan password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}