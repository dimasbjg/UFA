package com.dimdimbjg.ufa.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

        val date = "$year-$month-$day"

        val dateString: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDateTime = LocalDateTime.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            formatter.format(localDateTime)
        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            formatter.format(parser.parse(date))
        }

        return dateString

    }

}