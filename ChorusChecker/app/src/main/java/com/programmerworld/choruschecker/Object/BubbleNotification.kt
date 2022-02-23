package com.programmerworld.choruschecker.Object

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import com.programmerworld.choruschecker.ActivityClass.BubbleActivity
import com.programmerworld.choruschecker.DataClass.MessageChat
import com.programmerworld.choruschecker.DataClass.MessageNotification
import com.programmerworld.choruschecker.DataClass.Song
import com.programmerworld.choruschecker.Object.Container.itemTemp
import com.programmerworld.choruschecker.Object.Container.simpleMessageTemp
import com.programmerworld.choruschecker.Object.HistoryChat.MESSAGES
import com.programmerworld.choruschecker.Object.Notifications.BM_ID
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.SupportClass.DirectReplyReceiver

@SuppressLint("StaticFieldLeak")
object BubbleNotification{

    private const val REQUEST_CONTENT = 1
    private const val REQUEST_BUBBLE = 2

    private lateinit var context: Context
    private lateinit var notificationManager: NotificationManager
    private lateinit var shortcutManager: ShortcutManager

    private lateinit var iconCmp : IconCompat
    private lateinit var iconPerson : Icon
    private lateinit var contentUriCmp : Uri

    fun showNotificationCompat(con: Context, simpleMessage: MessageNotification, item: Song, me: Boolean) {

        context = con
        notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        shortcutManager = context.getSystemService(AppCompatActivity.SHORTCUT_SERVICE) as ShortcutManager

        iconCmp = IconCompat.createWithResource(context, simpleMessage.image)
        iconPerson = Icon.createWithResource(context, simpleMessage.image)
        contentUriCmp = createContentUri(simpleMessage.sender)

        itemTemp = item
        simpleMessageTemp = simpleMessage

        val builder = getNotificationCompatBuilder()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val person = Person.Builder()
                    .setName(simpleMessage.sender)
                    .setIcon(iconPerson)
                    .setImportant(true)
                    .build()

            val personStyle = androidx.core.app.Person.Builder()
                    .setName(simpleMessage.sender)
                    .setIcon(iconCmp)
                    .setImportant(true)
                    .build()

            val bubbleData = createCompatBubbleMetadata(contentUriCmp, iconCmp, item)

            val shortcut = createDynamicShortcut(
                    simpleMessage,
                    iconPerson,
                    person,
                    item
            )
            addDynamicShortcut(shortcut)
            builder.setBubbleMetadata(bubbleData)

            val messagingStyle = NotificationCompat.MessagingStyle(personStyle)

            val newMex = MessageChat(simpleMessage.text, item.author, System.currentTimeMillis(), item, me)
            MESSAGES.add(newMex)

            for (chatMessage in MESSAGES) {
                var notificationMessage : NotificationCompat.MessagingStyle.Message

                if(chatMessage.mus?.author.equals(item.author) && chatMessage.mus?.name.equals(item.name)){
                    if(chatMessage.isMe){
                        //"ME"
                        notificationMessage = NotificationCompat.MessagingStyle.Message(
                                "ME : ${chatMessage.message}",
                                chatMessage.time,
                                chatMessage.id
                        )
                    }
                    else{
                        //"BOT"
                        notificationMessage = NotificationCompat.MessagingStyle.Message(
                                "BOT : ${chatMessage.message}",
                                chatMessage.time,
                                chatMessage.id
                        )
                    }
                    messagingStyle.addMessage(notificationMessage)
                }
            }
            builder.setStyle(messagingStyle)
            builder.setShortcutId(shortcut.id)
            builder.addPerson(personStyle)
        }

        //Essentials
        builder.setContentTitle(
                context.resources.getString(
                        R.string.message_from,
                        simpleMessage.sender
                )
        )
        builder.setSmallIcon(R.drawable.ic_stat_notification)
        builder.setCategory(Notification.CATEGORY_MESSAGE)

        //Reply button
        itemTemp = item
        simpleMessageTemp = simpleMessage
        builder.addAction(
                NotificationCompat.Action
                        .Builder(
                                IconCompat.createWithResource(context, R.drawable.ic_play),
                                "Reply",
                                PendingIntent.getBroadcast(
                                        context,
                                        REQUEST_CONTENT,
                                        Intent(context, DirectReplyReceiver::class.java)
                                                .setData(contentUriCmp),
                                        PendingIntent.FLAG_UPDATE_CURRENT
                                )
                        )
                        .addRemoteInput(
                                androidx.core.app.RemoteInput.Builder("KEY_TEXT_REPLY")
                                        .setLabel("Your answer . . .")
                                        .build()
                        )
                        .setAllowGeneratedReplies(true)
                        .build()
        )

        val tmp = Intent(context, BubbleActivity::class.java)
        tmp.putExtra("name", item.name)
        tmp.putExtra("author", item.author)
        tmp.putExtra("logo", item.logo.toString())
        builder.setContentIntent(
                PendingIntent.getActivity(
                        context,
                        REQUEST_CONTENT,
                        tmp
                                .setAction(Intent.ACTION_VIEW)
                                .setData(contentUriCmp),
                        PendingIntent.FLAG_UPDATE_CURRENT
                )
        )
        builder.setShowWhen(true)

        notificationManager.notify(simpleMessage.id, builder.build())
    }

    private fun getNotificationCompatBuilder(): NotificationCompat.Builder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context,BM_ID)
        } else {
            NotificationCompat.Builder(context)
        }
    }

    private fun addDynamicShortcut(shortcut: ShortcutInfo) {
        if (atLeastAndroid11()) {
            shortcutManager.pushDynamicShortcut(shortcut)
        } else {
            shortcutManager.addDynamicShortcuts(listOf(shortcut))
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createCompatBubbleMetadata(
        contentUri: Uri,
        icon: IconCompat,
        item: Song
    ): NotificationCompat.BubbleMetadata {
        // Create bubble intent
        val tmp = Intent(context, BubbleActivity::class.java)
        tmp.putExtra("name", item.name)
        tmp.putExtra("author", item.author)
        val tmp2 = -1
        tmp.putExtra("logo", tmp2.toString())
        val bubbleIntent =
            PendingIntent.getActivity(
                context,
                REQUEST_BUBBLE,
                // Launch BubbleActivity as the expanded bubble.
                tmp
                    .setAction(Intent.ACTION_VIEW)
                    .setData(contentUri),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        // Create bubble metadata
        val builder = if (atLeastAndroid11()) {
            NotificationCompat.BubbleMetadata.Builder(bubbleIntent, icon)
        } else {
            NotificationCompat.BubbleMetadata.Builder()
                .setIntent(bubbleIntent)
                .setIcon(icon)
        }
        return builder
            .setDesiredHeightResId(R.dimen.bubble_height)
            .setAutoExpandBubble(true)
            .setSuppressNotification(true)
            .build()
    }

    private fun atLeastAndroid11() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    private fun createContentUri(text: String): Uri {
        return text.toUri()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun createDynamicShortcut(
        message: MessageNotification,
        icon: Icon,
        person: Person,
        item: Song
    ): ShortcutInfo {
        val tmp = Intent(context, BubbleActivity::class.java)
        tmp.putExtra("name", item.name)
        tmp.putExtra("author", item.author)
        tmp.putExtra("logo", item.logo.toString())
        return ShortcutInfo.Builder(context, message.id.toString())
            .setLongLived(true)
            .setIntent(
                tmp
                    .setAction(Intent.ACTION_VIEW)
                    .setData(createContentUri(message.text))
            )
            .setShortLabel(message.sender)
            .setIcon(icon)
            .setPerson(person)
            .build()
    }
}