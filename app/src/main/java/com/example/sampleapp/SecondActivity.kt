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
//        findViewById<Button>(R.id.button8).setOnClickListener { dismissInapp() }
        findViewById<Button>(R.id.button8).setOnClickListener { goBack() }
        findViewById<Button>(R.id.enable).setOnClickListener { enableMoE() }
        findViewById<Button>(R.id.disable).setOnClickListener { disableMoE() }
    }

    fun dismissInapp(data: SelfHandledCampaignData) {
        Log.d("BannerFunction", "Button is working")
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
            Log.d("Banner1", "onStart() : onSelfHandledAvailable1() : $banner1")
            MoEInAppHelper.getInstance().setInAppContext(setOf("Chase2"))
            // Call Banner 2
            MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner2 ->
                MoEInAppHelper.getInstance().setInAppContext(setOf("Chase3"))
                Log.d("Banner2", "onStart() : onSelfHandledAvailable1() : $banner2")
                // Call Banner 3
                MoEInAppHelper.getInstance().getSelfHandledInApp(this) { banner3 ->
                    Log.d("Banner3", "onStart() : onSelfHandledAvailable1() : $banner3")
                }
            }
        }
//        val btn = findViewById<Button>(R.id.button5);
//            btn.setOnClickListener {
//                Log.d("dismissBanner","Banner 1 is dismissed")
//                if (banner1 != null) {
//                    dismissInapp(banner1)
//                }
//            }
    }
}


