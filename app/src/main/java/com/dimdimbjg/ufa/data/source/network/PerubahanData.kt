package com.dimdimbjg.ufa.data.source.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PerubahanData(
    val kategori: String = "",
    val detail: String = ""
) : Parcelable