package com.programmerworld.choruschecker.SupportClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.programmerworld.choruschecker.R
import com.programmerworld.choruschecker.DataClass.MessageChat

class MessagingAdapter() :
    RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<MessageChat>()
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv_bot_message: TextView = itemView.findViewById(R.id.tv_bot_message)
        private val tv_message: TextView = itemView.findViewById(R.id.tv_message)

        fun bind(word: MessageChat){
            when(word.id){
                "SEND_ID" -> {
                    tv_message.text = word.message
                    tv_message.visibility = View.VISIBLE
                    tv_bot_message.visibility = View.GONE
                }
                "RECEIVE_ID" -> {
                    tv_bot_message.text = word.message
                    tv_bot_message.visibility = View.VISIBLE
                    tv_message.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false) //ISTANZIARE INTERFACCIA UTENTE
        return MessageViewHolder(view) //CREARE ISTANZA DI FLOWERVIEWHOLDER
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messagesList[position])
    }

    fun insertMessage(message: MessageChat) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}