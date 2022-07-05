package com.rodsrpg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Registerscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerscreen)

        val register_email = findViewById<EditText>(R.id.et_register_email)
        val register_password = findViewById<EditText>(R.id.et_register_password)
        val registerbtn = findViewById<Button>(R.id.register_button)
        val returnbutton = findViewById<ImageView>(R.id.button_return)

        registerbtn.setOnClickListener {
            when{
                TextUtils.isEmpty(register_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Registerscreen,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(register_password.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@Registerscreen,
                        "Please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = register_email.text.toString().trim { it <= ' ' }
                    val password: String = register_password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@Registerscreen,
                                        "You are registered successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val intent = Intent(this@Registerscreen, Funcions::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {

                                    Toast.makeText(
                                        this@Registerscreen,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        )
                }
            }
        }

        returnbutton.setOnClickListener {

            val intent = Intent(this, mainscreen::class.java)
            startActivity(intent)

        }

    }
}