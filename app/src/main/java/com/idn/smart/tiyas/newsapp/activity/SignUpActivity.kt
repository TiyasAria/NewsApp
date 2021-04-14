package com.idn.smart.tiyas.newsapp.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.idn.smart.tiyas.newsapp.R
import com.idn.smart.tiyas.newsapp.databinding.ActivityForgotPasswordBinding
import com.idn.smart.tiyas.newsapp.databinding.ActivitySignInBinding
import com.idn.smart.tiyas.newsapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var signUpActivityBinding : ActivitySignUpBinding
    private lateinit var  mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserId : String = ""
    companion object{
        fun getLaunchService (from: Context) = Intent(from, SignUpActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpActivityBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpActivityBinding.root)

        signUpActivityBinding.ibBackSignup.setOnClickListener(this)
        signUpActivityBinding.btnSignup.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ib_back_signup -> startActivity(Intent(SignInActivity.getLaunchService(this)))
            R.id.btn_signup -> signUpUser()
        }
    }

    private fun signUpUser() {
        val fullName : String = signUpActivityBinding.etName.text.toString()
        val email : String = signUpActivityBinding.etEmail.text.toString()
        val password : String = signUpActivityBinding.etPassSignup.text.toString()
        val confirmPassword : String = signUpActivityBinding.etConfirmPassSignup.text.toString()
        if (fullName == ""){
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        }else if (email == ""){
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        }else if (password == ""){
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        }else if (confirmPassword == ""){
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child(getString(R.string.text_user)).child(firebaseUserId)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["fullname"] = fullName
                    userHashMap["email"] = email
                    userHashMap["linkedIn"] = ""
                    userHashMap["instagram"] = ""
                    userHashMap["medium"] = ""
                    userHashMap["photo"] = ""

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(MainActivity.getLaunchService(this)))
                            finish()
                        }
                    }
                }else{
                    val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
                    progress.hide()
                    Toast.makeText(this, getString(R.string.error_register) + it.exception!!
                        .message.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }
    }
}