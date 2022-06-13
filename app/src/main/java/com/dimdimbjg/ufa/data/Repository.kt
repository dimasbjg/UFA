package com.dimdimbjg.ufa.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dimdimbjg.ufa.data.source.network.*
import com.dimdimbjg.ufa.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Repository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance()

    private val _result = MutableLiveData<Exception?>()

    fun getProfile(liveData: MutableLiveData<Resource<UserData>>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveData.postValue(Resource.success(snapshot.getValue(UserData::class.java)))
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(Resource.error("Gagal terhubung dengan Server", null))
            }
        })

    }

    fun getJadwal(liveData: MutableLiveData<Resource<List<Jadwal>>>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid
        val jadwalRef = dbRef.getReference("kloter")


        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                val result = snapshot.getValue(UserData::class.java)

                if (result != null) {
                    jadwalRef.child(result.kloter)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val jadwalItems: List<Jadwal> = snapshot.children.map {
                                    it.getValue(Jadwal::class.java)!!
                                }
                                liveData.postValue(Resource.success(jadwalItems))
                                Log.d("jadwal", jadwalItems.toString())
                            }

                            override fun onCancelled(error: DatabaseError) {
                                liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                                    null))
                            }

                        })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                    null))
            }
        })


    }

    fun getInformasi(liveData: MutableLiveData<Resource<List<Informasi>>>) {
        val informasiRef = dbRef.getReference("informasi")


        informasiRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val informasiList: List<Informasi> = snapshot.children.map {
                    it.getValue(Informasi::class.java)!!
                }

                liveData.postValue(Resource.success(informasiList))

            }

            override fun onCancelled(error: DatabaseError) {

                liveData.postValue(Resource.error("Fail", null))
            }

        })


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

    fun getPeminjamanList(liveData: MutableLiveData<Resource<List<Peminjaman>>>) {
        val listPinjamanRef = dbRef.getReference("pinjaman")

        listPinjamanRef.child(firebaseAuth.uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listPeminjaman: List<Peminjaman> = snapshot.children.map {
                        it.getValue(Peminjaman::class.java)!!.copy(id = it.key!!)
                    }
                    liveData.postValue(Resource.success(listPeminjaman))
                }

                override fun onCancelled(error: DatabaseError) {
                    liveData.postValue(Resource.error("Terjadi kesalahan, tidak bisa terkoneksi dengan server",
                        null))
                }
            })
    }

    fun sendPinjaman(liveData: MutableLiveData<Resource<Peminjaman>>, peminjaman: Peminjaman) {
        val listPinjamanRef = dbRef.getReference("pinjaman")

        listPinjamanRef.child(firebaseAuth.uid!!).child(listPinjamanRef.child(firebaseAuth.uid!!).push().key.toString()).setValue(peminjaman)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    liveData.postValue(Resource.success(peminjaman))
                } else {
                    liveData.postValue(Resource.error("Gagal",null))
                }
            }
    }

    fun removePinjaman(id: String) {
        val listPinjamanRef = dbRef.getReference("pinjaman")

        listPinjamanRef.child(firebaseAuth.uid!!).child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (peminjaman in snapshot.children) {
                    peminjaman.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}



