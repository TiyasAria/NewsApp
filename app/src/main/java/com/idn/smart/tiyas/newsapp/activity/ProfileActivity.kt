package com.idn.smart.tiyas.newsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.idn.smart.tiyas.newsapp.R
import com.idn.smart.tiyas.newsapp.databinding.ActivityMainBinding
import com.idn.smart.tiyas.newsapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity(), View.OnClickListener{
    // untuk men clear suatu activity
    companion object{
        fun getLaunchService (from: Context) = Intent(from, ProfileActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    private lateinit var profileBinding : ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)
        supportActionBar?.hide()

        profileBinding.tvLogout.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_logout -> logOut()
            R.id.iv_back_profile -> startActivity(Intent())
        }
    }

    private fun logOut() {
        startActivity(Intent(SignInActivity.getLaunchService(this)))
        FirebaseAuth.getInstance().signOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(MainActivity.getLaunchService(this)))
    }
}