package ru.simakover.vkapi.presentation.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Util {

    fun mapTimestampToDate(timestamp: Long): String {
        val data = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(data)
    }

    fun <T> Flow<T>.mergeWith(secondFlow : Flow<T>) : Flow<T> {
        return merge(this, secondFlow)
    }
}