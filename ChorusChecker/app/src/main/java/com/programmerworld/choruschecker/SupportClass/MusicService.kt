package com.programmerworld.choruschecker.SupportClass

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.programmerworld.choruschecker.Interface.ActionPlaying
import com.programmerworld.choruschecker.Object.Variables.ACTION_BACKWARD
import com.programmerworld.choruschecker.Object.Variables.ACTION_FORWARD
import com.programmerworld.choruschecker.Object.Variables.ACTION_PLAY

class MusicService : Service() {

    private val mBinder: IBinder = MyBinder()
    var actionPlaying: ActionPlaying? = null
    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    inner class MyBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    override fun onStartCommand(intent: Intent, flags: Int, StartId: Int): Int {
        val actionName = intent.getStringExtra("INTENT")
        if (actionName != null) {
            when (actionName) {
                ACTION_PLAY -> if (actionPlaying != null) {
                    actionPlaying!!.playClicked()
                }
                ACTION_BACKWARD -> if (actionPlaying != null) {
                    actionPlaying!!.backwardClicked()
                }
                ACTION_FORWARD -> if (actionPlaying != null) {
                    actionPlaying!!.forwardClicked()
                }
            }
        }
        return START_REDELIVER_INTENT
    }

    fun setCallBack(actionPlaying: ActionPlaying?) {
        this.actionPlaying = actionPlaying
    }

}