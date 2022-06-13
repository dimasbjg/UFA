package com.dimdimbjg.ufa.data.source.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Peminjaman(
	val id: String = "",
	val barang: String = "",
	val jumlah: Int = 0,
	val untuk: String = "",
	val verified: Boolean = false
) : Parcelable

