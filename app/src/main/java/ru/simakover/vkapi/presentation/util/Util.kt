package ru.simakover.vkapi.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Util {

    fun mapTimestampToDate(timestamp: Long): String {
        val data = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(data)
    }
}