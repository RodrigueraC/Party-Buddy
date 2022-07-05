package com.rodsrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class mainscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginpage = findViewById<TextView>(R.id.logintext)

        val registerpage = findViewById<TextView>(R.id.registertext)

        loginpage.setOnClickListener {

            val intent = Intent(this, Loginscreen::class.java)
            startActivity(intent)

        }

        registerpage.setOnClickListener {

            val intent = Intent(this, Registerscreen::class.java)
            startActivity(intent)

        }
    }
}