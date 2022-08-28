package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class signup : AppCompatActivity() {
    val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        getSupportActionBar()?.hide();

        val signupemailm : EditText = findViewById(R.id.signupemail)
        val signuppasswordm : EditText = findViewById(R.id.signuppasword)
        val confirmpasswordm : EditText = findViewById(R.id.confirmpassword)
        val signup : TextView = findViewById(R.id.signinbutton)
        val backtologinm : TextView = findViewById(R.id.backtologin)


        val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()





        backtologinm.setOnClickListener{
            val backtologinIntent = Intent(this, MainActivity::class.java)
            startActivity(backtologinIntent)
        }

        signup.setOnClickListener{
            var email : String = signupemailm.getText().toString()
            var password : String = signuppasswordm.getText().toString()
            var confirmpassword : String = confirmpasswordm.getText().toString()


            if(email.isEmpty()){
                Toast.makeText(applicationContext,"Enter your email",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                Toast.makeText(applicationContext,"Password should be of more than 8 characters",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(confirmpassword!=password){
                Toast.makeText(applicationContext,"Both passwords doesn't match, Try Again!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(applicationContext, "Registration Successful, Please check your E-Mail for verification, also check your spam folder", Toast.LENGTH_LONG).show()
                            firebaseAuth.signOut()
                            finish()
                            val loginIntent = Intent(this, MainActivity::class.java)
                            startActivity(loginIntent)
                        }
                        else{
                            Toast.makeText(applicationContext, it.exception?.message , Toast.LENGTH_SHORT  ).show()
                        }
                    }

                }
            }
        }
    }
//    fun sendEmailVerification()
//    {
//        val firebaseUser : FirebaseUser? = firebaseAuth.getCurrentUser()
//        if(firebaseUser!=null){
//            firebaseUser.sendEmailVerification().addOnCompleteListener {
//                Toast.makeText(applicationContext, "Email Verification is sent, Verify and Log in again", Toast.LENGTH_SHORT).show()
//                    firebaseAuth.signOut()
//                finish()
//                val loginIntent = Intent(this, MainActivity::class.java)
//                startActivity(loginIntent)
//            }
//        }
//        else{
//            Toast.makeText(applicationContext, "Failed to send verification E-Mail", Toast.LENGTH_SHORT).show()
//        }
//    }
}