package com.programmerworld.choruschecker.DataClass


data class MessageChat(
        val message: String,
        val id: String,
        val time: Long,
        val mus: Song?,
        val isMe: Boolean
    )