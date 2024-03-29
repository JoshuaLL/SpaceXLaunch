package com.joshua.spacexlaunch.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun shortenNumberAddPrefix(numberToFormat: Long): String {
    val number = numberToFormat.toDouble()
    val df = DecimalFormat("#.##").apply { roundingMode = RoundingMode.CEILING }
    return when {
        number > 1000000000 -> "${df.format(number.div(1000000000))} billion"
        number > 1000000 -> "${df.format(number.div(1000000))} million"
        else -> number.toInt().toString()
    }
}

fun getLocalTimeFromUnix(unixTime: Long): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    simpleDateFormat.timeZone = Calendar.getInstance().timeZone
    return simpleDateFormat.format(Date(unixTime * 1000))
}

fun getMonthValueFromUnixTime(unixTime: Long): Int {
    val instant = Instant.ofEpochSecond(unixTime)
    val localDataTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return localDataTime.monthValue
}

fun getYearValueFromUnixTime(unixTime: Long): Int {
    val instant = Instant.ofEpochSecond(unixTime)
    val localDataTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return localDataTime.year
}
