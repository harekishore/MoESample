package com.example.sampleapp

import android.app.Application
import android.content.Intent
import android.util.Log
import com.moengage.core.LogLevel
//import com.moengage.core.MoEngage
import com.moengage.core.config.*
import com.moengage.core.enableAdIdTracking
import com.moengage.example.push.CustomPushMessageListener
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.inbox.ui.view.InboxActivity
import com.moengage.pushbase.MoEPushHelper
import com.moengage.core.config.LogConfig

import com.moengage.core.DataCenter

import android.R
import android.content.Context
import android.os.Bundle

import com.moengage.core.config.NotificationConfig

import com.moengage.core.MoEngage
import com.moengage.inapp.MoEInAppHelper
import com.moengage.pushbase.push.PushMessageListener


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        val trackingOptOut = mutableSetOf<Class<*>>()
//
//        val trackingOptOutConfig = TrackingOptOutConfig(
//            isGaidTrackingEnabled = true,
//            isAndroidIdTrackingEnabled = true,
//            isCarrierTrackingEnabled = true,
//            isDeviceAttributeTrackingEnabled = true,
//            trackingOptOut
//        )
//
//        val moEngage = MoEngage.Builder(this, "9H3BB5EOSEKHUQC1M6ZQLAPR")
//            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
//            .configureTrackingOptOut(
//                trackingOptOutConfig
//            )
//            .configureNotificationMetaData(NotificationConfig(
//                smallIcon = R.drawable.small_icon,
//                largeIcon = R.drawable.download,
//                R.color.teal_700,
//                null,
//                false,
//                isBuildingBackStackEnabled = true,
//true
//            ))
//            .build()
//        MoEngage.initialise(moEngage)

        // New v12 config
        val moEngage = MoEngage.Builder(this, "2877NHMD0TOHATHC6NNHDERW")
            .configureLogs(LogConfig(LogLevel.DEBUG,isEnabledForReleaseBuild = true))
            .configureNotificationMetaData(
                NotificationConfig(
                    smallIcon = R.drawable.btn_default_small,
                    largeIcon = R.drawable.btn_default,
                    R.color.holo_green_dark,
                    isMultipleNotificationInDrawerEnabled = true,
                    true,
                    isLargeIconDisplayEnabled = true,
            )
            )
            .configureCards(CardConfig(
                isSwipeRefreshEnabled = true
            ))
            .configureTrackingOptOut(TrackingOptOutConfig(
                isCarrierTrackingEnabled = true,
                isDeviceAttributeTrackingEnabled = true,

            ))
            .build()
        MoEngage.initialiseDefaultInstance(moEngage)
//        enableAdIdTracking(this)
        setupPushCallbacks()
        setupInAppCallbacks()
    }

    private fun setupPushCallbacks() {
        // callback for notification events and notification customisation point.
        MoEPushHelper.getInstance().registerMessageListener(CustomPushMessageListener())
        // Callback for Firebase Token
        MoEFireBaseHelper.getInstance().addTokenListener { token ->
            Log.d( "test", " fcm token: ${token.pushToken}" )
        }
    }

    private fun setupInAppCallbacks() {
//        // callback for in-app campaign click
//        MoEInAppHelper.getInstance().setClickActionListener(ClickActionCallback())
//        // callback for in-app lifecycle - campaign shown/dismissed.
//        MoEInAppHelper.getInstance().addInAppLifeCycleListener(InAppLifecycleCallbacks())
        // callback for self handled campaigns that are triggered based on events.
        MoEInAppHelper.getInstance().setSelfHandledListener(SelfHandledCallback())

    }

}

