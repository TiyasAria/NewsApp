package com.idn.smart.tiyas.newsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        setContentView(R.layout.activity_forgot_password)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}