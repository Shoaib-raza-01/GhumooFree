package com.example.ghumoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
//import com.google.android.gms.auth.api.signin.GoogleSignInClient

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    lateinit var tabLayout: TabLayout
    lateinit var viewPAger: ViewPager
    var viewPagerAdapter : ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val facebookBtn = findViewById<ImageView>(R.id.facebook)
        val googleBtn = findViewById<ImageView>(R.id.google)

        // for the swiping fragments
        viewPAger = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPAger.adapter = viewPagerAdapter

        //it is used to join TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPAger)

        //for google and facebook sign in

        googleBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Hello motto", Toast.LENGTH_SHORT).show()
        }
    }
}

//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/Shoaib-raza-01/GhumooFree.git
//git push -u origin main