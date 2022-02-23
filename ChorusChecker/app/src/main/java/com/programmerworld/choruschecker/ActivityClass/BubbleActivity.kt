package com.programmerworld.choruschecker.ActivityClass

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programmerworld.choruschecker.DataClass.MessageChat
import com.programmerworld.choruschecker.DataClass.MessageNotification
import com.programmerworld.choruschecker.DataClass.Song
import com.programmerworld.choruschecker.Object.BotResponse
import com.programmerworld.choruschecker.Object.BotResponse.basicQuestion
import com.programmerworld.choruschecker.Object.BubbleNotification.showNotificationCompat
import com.programmerworld.choruschecker.Object.HistoryChat.MESSAGES
import com.programmerworld.choruschecker.Object.Variables.Colgandoentusmanos
import com.programmerworld.choruschecker.Object.Variables.Cuandomeenamoro
import com.programmerworld.choruschecker.Object.Variables.Lethergo
import com.programmerworld.choruschecker.Object.Variables.Loveinthedark
import com.programmerworld.choruschecker.Object.Variables.Mifidodite
import com.programmerworld.choruschecker.Object.Variables.Nonmelospiegare
import com.programmerworld.choruschecker.Object.Variables.Photograph
import com.programmerworld.choruschecker.Object.Variables.QuestionsIntVariables
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.SupportClass.MessagingAdapter
import kotlinx.coroutines.*


class BubbleActivity : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
    private lateinit var rv_messages: RecyclerView
    private lateinit var btn_send : Button
    private lateinit var input_message : EditText
    private lateinit var Rtitle: String
    private lateinit var Rauthor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bubble_activity)

        val tmp = getIntent().getStringExtra("logo")!!.toInt()

        if(tmp != -1) {
            val notificationManager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(tmp)
        }

        rv_messages = findViewById(R.id.rv_messages)
        btn_send = findViewById(R.id.btn_send)
        input_message = findViewById(R.id.input_message)

        Rtitle = getIntent().getStringExtra("name").toString()
        Rauthor = getIntent().getStringExtra("author").toString()

        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

        btn_send.setOnClickListener {
            sendMessage()
        }

        input_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }

        for (chatMessage in MESSAGES) {

            if(chatMessage.mus?.author.equals(Rauthor) && chatMessage.mus?.name.equals(Rtitle)){
                if(chatMessage.isMe){
                    //"ME"
                    adapter.insertMessage(MessageChat(chatMessage.message, "SEND_ID", System.currentTimeMillis(), chatMessage.mus, chatMessage.isMe))
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
                else{
                    //"BOT"
                    adapter.insertMessage(MessageChat(chatMessage.message, "RECEIVE_ID", System.currentTimeMillis(), chatMessage.mus, chatMessage.isMe))
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }

        }

    }
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    fun sendMessageReplyBtn(messaggio : MessageNotification, mus : Song, con : Context) {
        val message = messaggio.text

        if (message.isNotEmpty()) {
            botResponseReplyBtn(message, mus, con)
        }
    }

    private fun sendMessage() {
        val message = input_message.text.toString()

        if (message.isNotEmpty()) {
            input_message.setText("")

            adapter.insertMessage(MessageChat(message, "SEND_ID", System.currentTimeMillis(), null, true))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = System.currentTimeMillis()

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                //Risposta del bot
                val response = BotResponse.basicResponses(message,Rtitle,QuestionsIntVariables(Rtitle))

                adapter.insertMessage(MessageChat(response, "RECEIVE_ID", timeStamp, null, false))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                customBotMessage(basicQuestion(Rtitle,QuestionsIntVariables(Rtitle)))
            }
        }
    }

    private fun botResponseReplyBtn(message: String, mus : Song, con : Context) {
        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message, mus.name, QuestionsIntVariables(mus.name))

                showNotificationCompat(con, MessageNotification(mus.logo, mus.author, response, mus.logo), mus, false)

                customBotMessageReplyBtn(basicQuestion(mus.name,QuestionsIntVariables(mus.name)), con, mus)
            }
        }
    }

    private fun customBotMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = System.currentTimeMillis()

                adapter.insertMessage(MessageChat(message, "RECEIVE_ID", timeStamp, null, false))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun customBotMessageReplyBtn(message: String, con : Context, mus : Song) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                showNotificationCompat(con, MessageNotification(mus.logo, mus.author, message, mus.logo), mus, false)
            }
        }
    }

    override fun onPause(){
        super.onPause()

        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putInt("Loveinthedark", Loveinthedark)
        editor.putInt("Lethergo", Lethergo)
        editor.putInt("Photograph", Photograph)
        editor.putInt("Colgandoentusmanos", Colgandoentusmanos)
        editor.putInt("Cuandomeenamoro", Cuandomeenamoro)
        editor.putInt("Mifidodite", Mifidodite)
        editor.putInt("Nonmelospiegare", Nonmelospiegare)

        editor.apply()
    }
}
