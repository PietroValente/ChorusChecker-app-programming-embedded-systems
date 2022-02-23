package com.programmerworld.choruschecker.SupportClass

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.programmerworld.choruschecker.ActivityClass.BubbleActivity
import com.programmerworld.choruschecker.DataClass.MessageNotification
import com.programmerworld.choruschecker.Object.BubbleNotification.showNotificationCompat
import com.programmerworld.choruschecker.Object.Container.itemTemp
import com.programmerworld.choruschecker.Object.Container.simpleMessageTemp

class DirectReplyReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val replyText = remoteInput.getCharSequence("KEY_TEXT_REPLY").toString()

            val mex = MessageNotification(simpleMessageTemp.id, itemTemp.author, replyText, simpleMessageTemp.image)

            //Notification
            showNotificationCompat(context, mex, itemTemp, true)

            //Message in the chat bubble
            BubbleActivity().sendMessageReplyBtn(mex, itemTemp, context)
        }
    }
}