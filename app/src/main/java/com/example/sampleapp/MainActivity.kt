package com.example.sampleapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView
import com.moengage.cards.ui.CardActivity
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inbox.ui.view.InboxActivity

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawer: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawer = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        navView = findViewById(R.id.navView)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.button).setOnClickListener { trackUser() }
        findViewById<Button>(R.id.button6).setOnClickListener { appInbox() }
        findViewById<Button>(R.id.button7).setOnClickListener { cards() }
//        var nudge = findViewById<com.moengage.widgets.NudgeView>(R.id.nudge)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.others -> {
                    MoEAnalyticsHelper.trackDeviceLocale(this);
                    Log.i("MyCode", "others clicked")
                }
                R.id.triggerEvent -> {
                    MoEAnalyticsHelper.trackEvent(
                        this,
                        "addToCart",
                        Properties().addAttribute("itemName", "iphone")
                    )
                    Log.i("MyCode", "trigger events clicked")
                }
                R.id.logout -> {
                    MoECoreHelper.logoutUser(this)
                    Log.i("MyCode", "logout clicked")
                }
                R.id.nextActivity -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                    Log.i("MyCode", "activity2 clicked")
                }
            }
            drawer.closeDrawers()
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun cards() {
        val intent = Intent(this, CardActivity::class.java)
        startActivity(intent)
    }

    private fun trackUser() {
        val name: String = findViewById<TextView?>(R.id.name).text.toString()
        val email: String = findViewById<TextView?>(R.id.email).text.toString()

        MoEAnalyticsHelper.setUniqueId(this, "$email")
        MoEAnalyticsHelper.setEmailId(this, "$email")
        MoEAnalyticsHelper.setFirstName(this, "$name")

    }

    private fun appInbox() {
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG", "Main onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG", "Main onResume called...")
    }

    override fun onStart() {
        super.onStart()
        MoEInAppHelper.getInstance().resetInAppContext()
        MoEInAppHelper.getInstance().showInApp(this)
//        findViewById<com.moengage.widgets.NudgeView>(R.id.nudge).initialiseNudgeView(activity = Activity())
        Log.d("MYTAG", "Main onStart called...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYTAG", "Main onStop called...")
    }


}


