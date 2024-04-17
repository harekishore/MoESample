package com.example.sampleapp

import android.util.Log
import com.moengage.inapp.listeners.SelfHandledAvailableListener
import com.moengage.inapp.model.SelfHandledCampaignData

class SelfHandledCallback: SelfHandledAvailableListener {

    override fun onSelfHandledAvailable(data: SelfHandledCampaignData?) {
        Log.e("CallbackinappData"," onSelfHandledAvailable() $data")
    }
}