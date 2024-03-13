package com.moengage.example.push

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.moengage.pushbase.push.PushMessageListener

/**
 * @author Umang Chamaria/
 * Date: 2022/02/10
 */
class CustomPushMessageListener: PushMessageListener() {

    override fun onNotificationReceived(context: Context, payload: Bundle) {
        super.onNotificationReceived(context, payload)
        Log.d("test", payload.toString());
    }

    override fun onNotificationCleared(context: Context, payload: Bundle) {
        super.onNotificationCleared(context, payload)
        Log.d("test", payload.toString());
    }
}