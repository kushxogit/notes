package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var letsgo : TextView = findViewById(R.id.letsgo)

        letsgo.setOnClickListener{
            var intent =Intent(this,notesactivity::class.java)
            startActivity(intent)
        }
    }
}