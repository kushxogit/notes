package com.example.notes

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class forgotpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        getSupportActionBar()?.hide();

       var forgotemailm : EditText = findViewById(R.id.forgotemail)
        var forgotrecoverm : TextView = findViewById(R.id.forgotrecover)
        var forgotlogin : TextView = findViewById(R.id.forgotlogin)

        var firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()



        forgotlogin.setOnClickListener{
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

        forgotrecoverm.setOnClickListener{
            var email : String = forgotemailm.getText().toString()
            if(email.isEmpty()){
                Toast.makeText(applicationContext, "Enter your email first",Toast.LENGTH_SHORT).show()
            }
            else{
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(applicationContext,"Mail sent, You can recover your password using the sent mail, also check your Spam folder", Toast.LENGTH_LONG).show()
                        finish()
                        val loginIntent = Intent(this, MainActivity::class.java)
                        startActivity(loginIntent)
                    }
                    else{
                        Toast.makeText(applicationContext,"E-Mail does not exist", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}