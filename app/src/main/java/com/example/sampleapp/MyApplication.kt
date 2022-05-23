package com.example.sampleapp

import android.app.Application
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.config.TrackingOptOutConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val trackingOptOut = mutableSetOf<Class<*>>()

        val trackingOptOutConfig = TrackingOptOutConfig(
            isGaidTrackingEnabled = true,
            isAndroidIdTrackingEnabled = true,
            isCarrierTrackingEnabled = true,
            isDeviceAttributeTrackingEnabled = true,
            trackingOptOut
        )

        val moEngage = MoEngage.Builder(this, "9H3BB5EOSEKHUQC1M6ZQLAPR")
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureTrackingOptOut(
                trackingOptOutConfig
            )
            .configureNotificationMetaData(NotificationConfig(
                smallIcon = R.drawable.small_icon,
                largeIcon = R.drawable.download,
                R.color.teal_700,
                null,
                false,
                isBuildingBackStackEnabled = true,
true
            ))
            .build()
        MoEngage.initialise(moEngage)
    }

}