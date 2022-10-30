package com.bilal.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.bilal.finalproject.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

//    lateinit var binding : ActivityForgotPasswordBinding
//    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        setContentView(binding.root)
//
//        binding.btnSend.setOnClickListener {
//            val email = binding.edtEmailReset.text.toString()
//            val edtEmail = binding.edtEmailReset
//
//            if (email.isEmpty()) {
//                edtEmail.error = "Email Tidak Boleh Kosong"
//                edtEmail.requestFocus()
//                return@setOnClickListener
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                edtEmail.error = "Email Tidak Valid"
//                edtEmail.requestFocus()
//                return@setOnClickListener
//            }
//
//            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
//
//                if (it.isSuccessful){
//                    Toast.makeText(this, "Email Reset Password Telah Dikirim", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//

//        }
    }
}