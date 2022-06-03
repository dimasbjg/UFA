package com.dimdimbjg.ufa.data.source.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
	val alamat: String = "",
	val kelamin: Boolean = true,
	val kloter: String = "",
	val kewarganegaraan: String = "",
	val menikah: Boolean = false,
	val nama: String = "Nama Lengkap",
	val nik: Long = 0L,
	val nokk: Long = 0L,
	val orangtua1: String = "",
	val orangtua2: String = "",
	val tempatlahir: String = "",
	val umur: Int = 0,
	var pengajuanPerubahan: Boolean = false,
	val tanggallahir: Int = 999900,
	var verified: Boolean = false,
	var requestverify: Boolean = false
): Parcelable
