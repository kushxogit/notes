package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();


        val loginemailm : EditText = findViewById(R.id.loginemail)
        val loginpasswordm : EditText = findViewById(R.id.loginpasword)
        val loginbuttonm : TextView = findViewById(R.id.loginbutton)
        val forgotpasswordm : TextView = findViewById(R.id.forgot)
        val signupm : TextView = findViewById(R.id.signin)
        val skipm : TextView = findViewById(R.id.skip)




        forgotpasswordm.setOnClickListener{
            val forgotpasswordIntent = Intent(this, forgotpassword::class.java)
            startActivity(forgotpasswordIntent)
        }

        signupm.setOnClickListener{
            val signupIntent = Intent(this, signup::class.java)
            startActivity(signupIntent)
        }

        loginbuttonm.setOnClickListener{
            var email : String = loginemailm.getText().toString()
            var password : String = loginpasswordm.getText().toString()

            if(email.isEmpty()){
                Toast.makeText(applicationContext,"Enter your email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                Toast.makeText(applicationContext,"Password should be of more than 8 characters",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
                 }
            else{
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
//                        var firebaseUser : FirebaseUser? = FirebaseAuth.getInstance().currentUser
                        checkMailVerification()
                    }
                    else{
                        Toast.makeText(applicationContext,"Account doesn't exist",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkMailVerification() {
        var firebaseUser : FirebaseUser? = firebaseAuth.currentUser

        if(firebaseUser?.isEmailVerified() == true){
            Toast.makeText(applicationContext,"Logged in",Toast.LENGTH_SHORT).show()
            finish()
            val getstartedIntent = Intent(this, details::class.java)
            startActivity(getstartedIntent)

        }
        else{
            Toast.makeText(applicationContext,"Verify your User",Toast.LENGTH_SHORT).show()
            firebaseAuth.signOut()
        }
    }
}

