package com.programmerworld.choruschecker.DataClass

import androidx.annotation.DrawableRes

data class MessageNotification(
    val id: Int,
    val sender: String,
    val text: String,
    @DrawableRes val image: Int
    )
