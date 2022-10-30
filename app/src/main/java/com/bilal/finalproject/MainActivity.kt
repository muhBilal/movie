package com.bilal.finalproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.bilal.finalproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val btnSignIn = findViewById<Button>(R.id.btn_send)
        val etEmail = findViewById<TextInputEditText>(R.id.eMail)
        val etPassword = findViewById<TextInputEditText>(R.id.passwords)
        val btnregis = findViewById<TextView>(R.id.singUp)
        val forgotPassword = findViewById<TextView>(R.id.tv_forgotPassword)

        btnSignIn.setOnClickListener {
            signInFirebase(etEmail.text.toString(), etPassword.text.toString())
        }

        btnregis.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }

        forgotPassword.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            overridePendingTransition(0, 0)
        }

    }

    private fun signInFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    storeEmailToPreference(user!!.email!!)
                    startActivity(Intent(this, HomeActivity::class.java).putExtra("user", user))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun storeEmailToPreference(email: String){
        val sharedPref = this.getSharedPreferences("user", Context.MODE_PRIVATE)

        sharedPref.edit().putString("EMAIL", email).apply()
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java).putExtra("user", currentUser.email))
            finish()
        }
    }
}