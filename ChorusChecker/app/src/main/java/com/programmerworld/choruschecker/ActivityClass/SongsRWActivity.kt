package com.programmerworld.choruschecker.ActivityClass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programmerworld.choruschecker.DataClass.MessageNotification
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.DataClass.Song
import com.programmerworld.choruschecker.Object.BotResponse
import com.programmerworld.choruschecker.Object.BubbleNotification.showNotificationCompat
import com.programmerworld.choruschecker.Object.HistoryChat.MESSAGES
import com.programmerworld.choruschecker.Object.Variables
import com.programmerworld.choruschecker.Object.Variables.Colgandoentusmanos
import com.programmerworld.choruschecker.Object.Variables.Cuandomeenamoro
import com.programmerworld.choruschecker.Object.Variables.Lethergo
import com.programmerworld.choruschecker.Object.Variables.Loveinthedark
import com.programmerworld.choruschecker.Object.Variables.Mifidodite
import com.programmerworld.choruschecker.Object.Variables.Nonmelospiegare
import com.programmerworld.choruschecker.Object.Variables.Photograph
import com.programmerworld.choruschecker.SupportClass.OnSongItemClickListner
import com.programmerworld.choruschecker.SupportClass.SongAdapter

class SongsRWActivity : AppCompatActivity(), OnSongItemClickListner {

    lateinit var songlist: ArrayList<Song>

    private val KEY_S1 = "KEY_SONG_1"
    private val KEY_S2 = "KEY_SONG_2"
    private val KEY_S3 = "KEY_SONG_3"
    private val KEY_S4 = "KEY_SONG_4"
    private val KEY_S5 = "KEY_SONG_5"
    private val KEY_S6 = "KEY_SONG_6"
    private val KEY_S7 = "KEY_SONG_7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_recycle_view)
        songlist = ArrayList()
        addSongs()

        val recyclerView: RecyclerView = findViewById(R.id.song_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,1))
        recyclerView.adapter = SongAdapter(songlist,this)

        val preferences = getPreferences(MODE_PRIVATE)
        Loveinthedark = preferences.getInt("Loveinthedark", 1)
        Lethergo = preferences.getInt("Lethergo", 1)
        Photograph = preferences.getInt("Photograph", 1)
        Colgandoentusmanos = preferences.getInt("Colgandoentusmanos", 1)
        Cuandomeenamoro = preferences.getInt("Cuandomeenamoro", 1)
        Mifidodite = preferences.getInt("Mifidodite", 1)
        Nonmelospiegare = preferences.getInt("Nonmelospiegare", 1)
    }

    fun addSongs(){
        val preferences = getPreferences(MODE_PRIVATE)
        songlist.add(Song("Let Her Go","Passenger", R.drawable.lethergo, R.raw.lethergo,"english",preferences.getBoolean(KEY_S1, false)))
        songlist.add(Song("Love in the dark","Adele", R.drawable.loveinthedark, R.raw.loveinthedark,"english",preferences.getBoolean(KEY_S2, false)))
        songlist.add(Song("Photograph","Ed Sheeran", R.drawable.photograph, R.raw.photograph,"english",preferences.getBoolean(KEY_S3, false)))
        songlist.add(Song("Colgando en tus manos","Carlos Baute", R.drawable.colgandoentusmanos, R.raw.colgandoentusmanos,"spanish",preferences.getBoolean(KEY_S4, false)))
        songlist.add(Song("Cuando me enamoro","Enrique Iglesias", R.drawable.cuandomeenamoro, R.raw.cuandomeenamoro,"spanish",preferences.getBoolean(KEY_S5, false)))
        songlist.add(Song("Mi fido di te","Jovanotti", R.drawable.mifidodite, R.raw.mifidodite,"italian",preferences.getBoolean(KEY_S6, false)))
        songlist.add(Song("Non me lo so spiegare","Tiziano Ferro", R.drawable.nonmelosospiegare, R.raw.nonmelosospiegare,"italian",preferences.getBoolean(KEY_S7, false)))
    }

    override fun onItemClick(item: Song, position: Int) {

        showNotificationCompat(this,MessageNotification(item.logo, item.author, "Hello, I'm "+ item.author +". I'm here to test how careful you have " +
                "been listening to my song " + item.name + "! Try to answer these questions:", item.logo), item, false)

        showNotificationCompat(this,MessageNotification(item.logo, item.author, BotResponse.basicQuestion(item.name, Variables.QuestionsIntVariables(item.name)), item.logo), item, false)

        val intent = Intent(this, MediaControlsActivity::class.java)

        intent.putExtra("name", item.name)
        intent.putExtra("author", item.author)
        intent.putExtra("logo", item.logo.toString())
        intent.putExtra("track", item.track.toString())

        startActivity(intent)
    }

    fun saveState(){
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putBoolean(KEY_S1, Lethergo == 6)
        editor.putBoolean(KEY_S2, Loveinthedark == 6)
        editor.putBoolean(KEY_S3, Photograph == 6)
        editor.putBoolean(KEY_S4, Colgandoentusmanos == 6)
        editor.putBoolean(KEY_S5, Cuandomeenamoro == 6)
        editor.putBoolean(KEY_S6, Mifidodite == 6)
        editor.putBoolean(KEY_S7, Nonmelospiegare == 6)

        editor.putInt("Loveinthedark", Loveinthedark)
        editor.putInt("Lethergo", Lethergo)
        editor.putInt("Photograph", Photograph)
        editor.putInt("Colgandoentusmanos", Colgandoentusmanos)
        editor.putInt("Cuandomeenamoro", Cuandomeenamoro)
        editor.putInt("Mifidodite", Mifidodite)
        editor.putInt("Nonmelospiegare", Nonmelospiegare)

        editor.apply()
    }

    override fun onPause()
    {
        saveState()
        super.onPause()
    }
}