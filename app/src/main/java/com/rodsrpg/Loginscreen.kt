package com.rodsrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Loginscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginscreen)

        val login_email = findViewById<EditText>(R.id.et_login_email)
        val login_password = findViewById<EditText>(R.id.et_login_password)
        val btn_login = findViewById<Button>(R.id.login_button)
        val returnbutton = findViewById<ImageView>(R.id.button_return)

        btn_login.setOnClickListener {
            when {
                TextUtils.isEmpty(login_email.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@Loginscreen,
                        "Please enter your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(login_password.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@Loginscreen,
                        "Please enter your password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = login_email.text.toString().trim{ it <= ' ' }
                    val password: String = login_password.text.toString().trim{ it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@Loginscreen,
                                    "You are logged in successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this@Loginscreen, Funcions::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@Loginscreen,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        returnbutton.setOnClickListener {

            val intent = Intent(this, mainscreen::class.java)
            startActivity(intent)

        }
    }
}