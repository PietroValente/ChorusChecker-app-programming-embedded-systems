package com.programmerworld.choruschecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.programmerworld.choruschecker.ActivityClass.SongsRWActivity
import com.programmerworld.choruschecker.Object.HistoryChat.MESSAGES
import com.programmerworld.choruschecker.Object.Notifications.setUpNotificationChannels

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app_button: Button = findViewById(R.id.app_button)
        val app_title: TextView = findViewById(R.id.app_title)
        val app_description: TextView = findViewById(R.id.app_description)

        setUpNotificationChannels(this)

        app_title.text = "Welcome to ChorusChecker"

        app_description.text = "This app can be used as a language lab software. We would like you to learn new terms enriching your grammar and lexical knowledge by enjoying some good music"

        app_button.setOnClickListener(){
            MESSAGES.clear()
            val intent = Intent(this, SongsRWActivity::class.java)
            startActivity(intent)
        }
    }
}