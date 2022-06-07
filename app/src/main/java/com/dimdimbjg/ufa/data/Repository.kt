package com.dimdimbjg.ufa.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.dimdimbjg.ufa.data.source.network.*
import com.dimdimbjg.ufa.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class Repository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance()

    private val _result = MutableLiveData<Exception?>()

    fun getProfile(liveData: MutableLiveData<Resource<UserData>>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid

        var connection = true

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (connection) {
                    liveData.postValue(Resource.success(snapshot.getValue(UserData::class.java)))
                }
                connection = false
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(Resource.error("Fail due to security reason", null))
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            if (connection) {
                connection = false
                liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                    null))
            }
        }, 10000)
    }

    fun getJadwal(liveData: MutableLiveData<Resource<List<Jadwal>>>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid
        val jadwalRef = dbRef.getReference("kloter")

        var connection = true

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (connection) {
                    val result = snapshot.getValue(UserData::class.java)

                    if (result != null) {
                        jadwalRef.child(result.kloter)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val jadwalItems: List<Jadwal> = snapshot.children.map {
                                        it.getValue(Jadwal::class.java)!!
                                    }

                                    liveData.postValue(Resource.success(jadwalItems))
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                                        null))
                                }

                            })
                    }
                }
                connection = false
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                    null))
            }

        })

        Handler(Looper.getMainLooper()).postDelayed({
            if (connection) {
                connection = false
                liveData.postValue(Resource.error("Terjadi kesalahan, silahkan periksa koneksi internet anda",
                    null))
            }
        }, 10000)

    }

    fun getInformasi(liveData: MutableLiveData<Resource<List<Informasi>>>) {
        val informasiRef = dbRef.getReference("informasi")

        var connection = true

        informasiRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (connection) {
                    connection = false
                    val informasiList: List<Informasi> = snapshot.children.map {
                        it.getValue(Informasi::class.java)!!
                    }

                    liveData.postValue(Resource.success(informasiList))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                connection = false
                liveData.postValue(Resource.error("Fail", null))
            }

        })

        Handler(Looper.getMainLooper()).postDelayed({
            if (connection) {
                connection = false
                liveData.postValue(Resource.error("Fail", null))
            }
        }, 10000)

    }

    fun updateProfile(userData: UserData, liveData: MutableLiveData<Resource<UserData>>) {
        dbRef.getReference("Users").child(firebaseAuth.uid!!).setValue(userData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    liveData.postValue(Resource.success(userData))
                } else {
                    _result.value = it.exception
                }
            }
    }

    fun sendPerubahan(
        perubahanData: PerubahanData,
        userData: UserData,
        liveData: MutableLiveData<Resource<PerubahanData>>,
    ) {

        dbRef.getReference("perubahan").child(firebaseAuth.uid!!).setValue(perubahanData)
            .addOnCompleteListener {

                userData.pengajuanPerubahan = true
                dbRef.getReference("Users").child(firebaseAuth.uid!!).setValue(userData)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            liveData.postValue(Resource.success(perubahanData))
                        } else {
                            liveData.postValue(Resource.error("Tidak bisa terhubung dengan sistem, silahkan coba beberapa saat lagi!",
                                null))
                        }
                    }
            }
    }

    fun getPeminjamanList (liveData: MutableLiveData<Resource<List<Peminjaman>>>) {
        val connection = true
        val listPinjamanRef = dbRef.getReference("pinjaman")

        listPinjamanRef.child(firebaseAuth.uid!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val listPeminjaman: List<Peminjaman> = snapshot.children.map {
                    it.getValue(Peminjaman::class.java)!!
                }
                liveData.postValue(Resource.success(listPeminjaman))
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server", null))
            }

        })


    }



}



