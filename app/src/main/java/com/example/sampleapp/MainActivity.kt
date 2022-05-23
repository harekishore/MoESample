package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.Properties

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.nextActivity).setOnClickListener { nextActivity() }
        findViewById<Button>(R.id.button).setOnClickListener { trackUser() }
        findViewById<Button>(R.id.button2).setOnClickListener { trackEvent() }
        findViewById<Button>(R.id.button3).setOnClickListener { logout() }
        findViewById<Button>(R.id.button4).setOnClickListener { others() }

    }

    fun nextActivity(){
       val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    private fun trackUser (){
        val name:String = findViewById<TextView?>(R.id.name).text.toString()
        val email:String = findViewById<TextView?>(R.id.email).text.toString()

        MoEHelper.getInstance(this).setUniqueId("$email")
        MoEHelper.getInstance(this).setEmail("$email")
        MoEHelper.getInstance(this).setFullName("$name")

    }

    private fun trackEvent (){
//        Log.d("Some Tag","This is an event log")
        MoEHelper.getInstance(this).trackEvent("addToCart", Properties().addAttribute("itemName","iphone"))
    }


    private fun logout(){
        MoEHelper.getInstance(this).logoutUser()
    }

    fun others(){
        MoEHelper.getInstance(applicationContext).trackDeviceLocale();
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG","Main onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG","Main onResume called...")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MYTAG","Main onStart called...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYTAG","Main onStop called...")
    }



//    private fun background(){
////        MoEHelper.getInstance(this).logoutUser()
//        Log.i("some","app iis in background")
//    }
}





