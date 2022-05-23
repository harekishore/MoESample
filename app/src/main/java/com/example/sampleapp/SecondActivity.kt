package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.button5).setOnClickListener { goBack() }

    }

    fun goBack(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG","Second onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG","Second onResume called...")
    }



}