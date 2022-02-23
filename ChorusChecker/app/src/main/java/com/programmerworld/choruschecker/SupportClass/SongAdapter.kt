package com.programmerworld.choruschecker.SupportClass

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.DataClass.Song

class SongAdapter(var items : ArrayList<Song>, var clickListner: OnSongItemClickListner)
    : RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    class SongViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        private val songName: TextView = itemView.findViewById(R.id.song_name)
        private val songAuthor: TextView = itemView.findViewById(R.id.song_author)
        private val songLanguage: TextView = itemView.findViewById(R.id.song_language)
        private val songLogo: ImageView = itemView.findViewById(R.id.song_logo)
        private val songCheck: CheckBox = itemView.findViewById(R.id.song_check)

        fun bind(item: Song, action:OnSongItemClickListner){
            songName.text = item.name
            songAuthor.text = item.author
            val tmp = item.language
            songLanguage.text = Html.fromHtml("<b>Language:</b> $tmp",0)
            songLogo.setImageResource(item.logo)
            songCheck.isChecked = item.check

            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }



    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(items.get(position),clickListner)
    }
}

interface OnSongItemClickListner{
    fun onItemClick(item: Song, position: Int)
}