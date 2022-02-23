package com.programmerworld.choruschecker.ActivityClass

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.programmerworld.choruschecker.Interface.ActionPlaying
import com.programmerworld.choruschecker.Object.Notifications.showMCNotification
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.SupportClass.MusicService

class MediaControlsActivity: AppCompatActivity(), ActionPlaying, ServiceConnection {

    private lateinit var notificationManager: NotificationManager
    private var mp: MediaPlayer? = null
    private lateinit var forward: ImageButton
    private lateinit var backward: ImageButton
    private lateinit var play: ImageButton
    private lateinit var title: TextView
    private lateinit var artist: TextView
    private lateinit var logo: ImageView
    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar
    private lateinit var RemainingTime : TextView
    private lateinit var CurrentTime : TextView
    private lateinit var stop : ImageButton
    private lateinit var repeat : ImageButton
    private var position : Int = 0
    private var isPlaying: Boolean = false
    private var musicService: MusicService? = null
    private var totalTime : Int = 0
    private var firstReproduction : Boolean = false

    private lateinit var Rtitle: String
    private lateinit var Rartist: String
    private var Rlogo: Int = 0
    private var Rtraccia: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_controls)

        notificationManager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        forward = findViewById(R.id.forward)
        backward = findViewById(R.id.backward)
        play = findViewById(R.id.play)
        stop = findViewById(R.id.buttonStop)
        repeat = findViewById(R.id.buttonRepeat)
        title = findViewById(R.id.titleSongs)
        artist = findViewById(R.id.textArtist)
        logo = findViewById(R.id.ImageAlbumArt)
        volumeBar = findViewById(R.id.volumeBar)
        positionBar = findViewById(R.id.playerSeekBar)
        RemainingTime = findViewById(R.id.remainingTime)
        CurrentTime = findViewById(R.id.CurrentTime)

        Rtitle = getIntent().getStringExtra("name").toString()
        Rartist = getIntent().getStringExtra("author").toString()
        Rlogo = getIntent().getStringExtra("logo")!!.toInt()
        Rtraccia = getIntent().getStringExtra("track")!!.toInt()

        mp = MediaPlayer.create(this, Rtraccia)
        mp?.setVolume(0.5f, 0.5f)
        totalTime = mp?.duration!!

        mp?.setOnCompletionListener {
            mp?.seekTo(0)
            playClicked()
            mp?.pause()
            isPlaying = false
        }

        repeat.setOnClickListener()
        {
            if(firstReproduction) {
                if (mp?.isPlaying == true) {
                    mp?.seekTo(0)
                } else {
                    mp?.seekTo(0)
                    playClicked()
                    isPlaying = true
                }
            }
        }

        stop.setOnClickListener()
        {
            if(mp?.isPlaying == true){
                playClicked()
                mp?.pause()
                mp?.seekTo(0)
                isPlaying = false
            }
            else
            {
                mp?.seekTo(0)
            }
        }

        forward.setOnClickListener{
            forwardClicked()
        }

        backward.setOnClickListener {
            backwardClicked()
        }

        play.setOnClickListener {
            playClicked()
        }

        logo.setImageResource(Rlogo)
        title.setText(Rtitle)
        artist.setText(Rartist)

        volumeBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            var volumeNum = progress / 100.0f
                            mp?.setVolume(volumeNum, volumeNum)
                        }
                    }
                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }
                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                }
        )

        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            mp?.seekTo(progress)
                        }
                    }
                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }
                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                }
        )

        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp?.currentPosition!!
                    handler.sendMessage(msg)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            positionBar.progress = currentPosition

            var elapsedTime = createTimeLabel(currentPosition)
            CurrentTime.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            RemainingTime.text = "-$remainingTime"
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    override fun onResume() {
        super.onResume()
        var intent : Intent = Intent(this, MusicService::class.java)
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        if (isPlaying) {
            isPlaying = false
            mp?.pause()
        }
        mp = null
        notificationManager.cancel(0)
        super.onDestroy()
    }

    override fun forwardClicked() {

        position = mp?.getCurrentPosition()!!;
        if (position + 30 <= mp!!.getDuration())
        {
            mp?.seekTo(position + 30000);
        }
        else
        {
            mp?.seekTo(mp?.getDuration()!!);
        }

        if(!isPlaying)
        {
            showMCNotification(this, R.drawable.ic_play, Rtitle, Rartist, Rlogo)
        }
        else
        {
            showMCNotification(this, R.drawable.ic_baseline_pause, Rtitle, Rartist, Rlogo)
        }
    }

    override fun backwardClicked() {

        position = mp!!.getCurrentPosition()
        if (position - 10 >= 0)
        {
            mp?.seekTo(position - 10000)
        }
        else
        {
            mp?.seekTo(0)
        }

        if(!isPlaying)
        {
            showMCNotification(this, R.drawable.ic_play, Rtitle, Rartist, Rlogo)
        }
        else
        {
            showMCNotification(this, R.drawable.ic_baseline_pause, Rtitle, Rartist, Rlogo)
        }
    }

    override fun playClicked() {

        firstReproduction = true
        if (mp?.isPlaying == true)
        {
            mp?.pause()

        }
        else
        {
            mp?.start()
        }

        if(!isPlaying)
        {
            isPlaying = true
            play.setImageResource(R.drawable.ic_baseline_pause)
            showMCNotification(this, R.drawable.ic_baseline_pause, Rtitle, Rartist, Rlogo)

        }
        else
        {
            isPlaying = false
            play.setImageResource(R.drawable.ic_play)
            showMCNotification(this, R.drawable.ic_play, Rtitle, Rartist, Rlogo)
        }

    }

    override fun onServiceDisconnected(name: ComponentName?) {
        musicService = null;
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        var binder : MusicService.MyBinder = service as MusicService.MyBinder
        musicService = binder.service
        musicService!!.setCallBack(this)
    }
}