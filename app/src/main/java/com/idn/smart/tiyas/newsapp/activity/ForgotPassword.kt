package com.idn.smart.tiyas.newsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.idn.smart.tiyas.newsapp.R
import com.idn.smart.tiyas.newsapp.databinding.ActivityForgotPasswordBinding
import com.idn.smart.tiyas.newsapp.databinding.ActivitySignInBinding

class ForgotPassword : AppCompatActivity(), View.OnClickListener {
    private lateinit var forgotPasswordBinding: ActivityForgotPasswordBinding
    private lateinit var  mAuth : FirebaseAuth
    companion object{
        fun getLaunchService (from: Context) = Intent(from, ForgotPassword::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(forgotPasswordBinding.root)
        supportActionBar?.hide()
        forgotPasswordBinding.ibBackForgot.setOnClickListener(this)
        forgotPasswordBinding.fbForgot.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.ib_back_forgot -> startActivity(SignInActivity.getLaunchService(this))
            R.id.fb_forgot -> forgotPassword()
        }
    }

    private fun forgotPassword() {
        val email = forgotPasswordBinding.etEmailForgot.text.toString()
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.error_text),  Toast.LENGTH_SHORT).show()
        }else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Check email to reset password", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(SignInActivity.getLaunchService(this)))
                }else{
                    Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}