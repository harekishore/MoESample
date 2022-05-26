package com.example.sampleapp

import android.app.Application
import android.content.Intent
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.*
import com.moengage.core.enableAdIdTracking
import com.moengage.inbox.ui.view.InboxActivity

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
            .configureLogs(LogConfig(LogLevel.VERBOSE))
            .configureNotificationMetaData(
                NotificationConfig(
                    smallIcon = R.drawable.small_icon,
                    largeIcon = R.drawable.large_icon,
                    R.color.purple_500,
                    isMultipleNotificationInDrawerEnabled = true,
                    true,
                    isLargeIconDisplayEnabled = true
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
        enableAdIdTracking(this)


    }

}