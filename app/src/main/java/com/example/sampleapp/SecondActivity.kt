package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.moengage.cards.core.MoECardHelper
import com.moengage.core.disableDataTracking
import com.moengage.inapp.MoEInAppHelper
import com.moengage.core.enableDataTracking
import com.moengage.inapp.model.SelfHandledCampaignData


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        findViewById<Button>(R.id.button5).setOnClickListener { dismissInapp() }
        findViewById<Button>(R.id.button5).setOnClickListener { goBack() }
        findViewById<Button>(R.id.enable).setOnClickListener { enableMoE() }
        findViewById<Button>(R.id.disable).setOnClickListener { disableMoE() }
    }

    fun dismissInapp(data: SelfHandledCampaignData) {
        Log.d("buttontest", "Button is working")
        MoEInAppHelper.getInstance().selfHandledDismissed(this, data)
    }

    fun goBack() {
        val intent = Intent(this, MainActivity::class.java)
        MoECardHelper.onCardSectionUnloaded(this)
        MoEInAppHelper.getInstance().resetInAppContext()
        startActivity(intent)
    }

    fun enableMoE() {
        enableDataTracking(this)
    }

    fun disableMoE() {
        disableDataTracking(this)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYTAG", "Second onpause called...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYTAG", "Second onResume called...")
    }

    override fun onStart() {
        super.onStart()
        MoEInAppHelper.getInstance().setInAppContext(setOf("Page1"))
        MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner1 ->
            val btn = findViewById<Button>(R.id.button5);
            btn.setOnClickListener {
                if (banner1 != null) {
                    dismissInapp(banner1)
                }
            }
            MoEInAppHelper.getInstance().setInAppContext(setOf("Chase2"))
            Log.d("Banner1", "onStart() : onSelfHandledAvailable1() : $banner1")
//            MoEInAppHelper.getInstance().selfHandledDismissed(this, banner1)
            // Call Banner 2
            MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner2 ->
                MoEInAppHelper.getInstance().setInAppContext(setOf("Chase3"))
                Log.d("Banner2", "onStart() : onSelfHandledAvailable1() : $banner2")
                // Call Banner 3
                MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner3 ->
                    Log.d("Banner3", "onStart() : onSelfHandledAvailable1() : $banner3")
                    MoEInAppHelper.getInstance().resetInAppContext()
                }
            }
        }
//                 Self handled cards implementation
//                 call on section or screen load
//        MoECardHelper.onCardSectionLoaded(this){}
//        var i = MoECardHelper.getCardCategories(this)
//        var j = MoECardHelper.getCardsForCategory(this, "Offers")
//        var m = MoECardHelper.getCardsForCategory(this, "Transactions")
//        var k = MoECardHelper.getCardsForCategory(this, CARDS_CATEGORY_ALL)
//        var l = MoECardHelper.getCardsInfo(this)
//        Log.w("cardCategories", "$i")
//        Log.w("Offers cardContent", "$j" )
//        Log.w("allContents", "$k" )
//        Log.w("allCategories & Content", "$l" )
//        Log.w("Transactions Cards", "$m" )
    }
}


