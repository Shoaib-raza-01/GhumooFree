package com.example.ghumoo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_manager)
        val user = Firebase.auth.currentUser
        if(user != null){
            startActivity(Intent(this,HomePage::class.java))
        }else{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}