package com.rodsrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Charactername : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val db = Firebase.firestore

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charactername)

        val Nome_usuario = findViewById<TextView>(R.id.default_welcome)

        val define_name = findViewById<EditText>(R.id.et_define_character_name)
        define_name.text.toString()

        val define_button = findViewById<Button>(R.id.define_name_button)
    }
}