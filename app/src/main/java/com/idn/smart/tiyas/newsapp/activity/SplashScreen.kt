package com.idn.smart.tiyas.newsapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.idn.smart.tiyas.newsapp.R

class SplashScreen : AppCompatActivity() {
    private lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        supportActionBar?.hide()
        Handler().postDelayed({
            if(user != null ){
                startActivity(MainActivity.getLaunchService(this))
                finish()
            }else{
                startActivity(SignInActivity.getLaunchService(this))
                finish()
            }
        }, 3000)
    }
}