package com.dimdimbjg.ufa.utils

import java.util.*
import kotlin.math.roundToInt

object CurrentDate {

    private val current = Calendar.getInstance()

    private val day = current.get(Calendar.DAY_OF_MONTH)
    private val month = current.get(Calendar.MONTH)+1
    private val year = current.get(Calendar.YEAR)
    private val hour = current.get(Calendar.HOUR)
    private val minute = current.get(Calendar.MINUTE)


    fun getDayInformat(): Int {
        return year*10000+month*100+day
    }

    fun getTime(): Int {
        return hour*100+minute
    }

}