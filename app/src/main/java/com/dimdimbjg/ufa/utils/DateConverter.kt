package com.dimdimbjg.ufa.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateConverter {

    @SuppressLint("SimpleDateFormat")
    fun convertInttoDate(longDate: Int): String {
        //current long format yyyymmdd
        //get year
        val year: Int = (longDate/10000)

        //get month
        val tempMont = longDate/100
        val month: Int = (tempMont%100)

        //get day
        val day: Int = (longDate%100)

        val date = String.format("%02d-%02d-$year", day,month).trim()

        return date
    }

    fun formattedTime(time: Int): String {
        //get hour
        val hour = time / 100

        //get munites
        val minute = time % 100

        return String.format("%02d:%02d",hour,minute)
    }

}