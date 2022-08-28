package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class notesactivity : AppCompatActivity() {

    var createnotesfab : FloatingActionButton = findViewById(R.id.createnotesicon)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notesactivity)
        supportActionBar?.setTitle("All Notes")


        createnotesfab.setOnClickListener{
            val createIntent = Intent(this,createnote::class.java)

        }

    }
}