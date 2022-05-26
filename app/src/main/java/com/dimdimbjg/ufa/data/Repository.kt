package com.dimdimbjg.ufa.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dimdimbjg.ufa.data.source.network.UserDataResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Repository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance()

    fun getProfile(liveData: MutableLiveData<UserDataResponse>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveData.postValue(snapshot.getValue(UserDataResponse::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

//    fun getProfile(): UserDataResponse {
//
//        val myRef = dbRef.getReference("Users")
//        val userId = FirebaseAuth.getInstance().currentUser!!.uid
//
//        var userData = UserDataResponse(
//            kewarganegaraan = "",
//            alamat = "",
//            kelamin = true,
//            kloter = 0,
//            nama = "Nama Lengkap",
//            nik = 0L,
//            nokk = 0L,
//            tanggallahir = 999900,
//            tempatlahir = "",
//            umur = 0,
//        )
//
//
//        myRef.child(userId).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val result = snapshot.getValue(UserDataResponse::class.java)!!
//
//                userData = result
//                Log.d("behasil",userData.nama)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("Test", "Gagal")
//                userData = UserDataResponse(
//                    kewarganegaraan = "",
//                    alamat = "",
//                    kelamin = true,
//                    kloter = 0,
//                    nama = "Nama Lengkap",
//                    nik = 0L,
//                    nokk = 0L,
//                    tanggallahir = 999900,
//                    tempatlahir = "",
//                    umur = 0,
//                )
//            }
//
//        })
//
//        return userData
//    }


}



