package com.dimdimbjg.ufa.data.source.network

data class UserDataResponse(
	val kewarganegaraan: String = "",
	val alamat: String = "",
	val kelamin: Boolean = true,
	val kloter: Int = 0,
	val nama: String = "Nama Lengkap",
	val nik: Long = 0L,
	val nokk: Long = 0L,
	val tanggallahir: Int = 999900,
	val tempatlahir: String = "",
	val umur: Int = 0,
)
