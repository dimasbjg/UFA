package com.dimdimbjg.ufa.data

import androidx.lifecycle.MutableLiveData
import com.dimdimbjg.ufa.data.source.network.Jadwal
import com.dimdimbjg.ufa.data.source.network.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class Repository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance()


    fun getProfile(liveData: MutableLiveData<UserData>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveData.postValue(snapshot.getValue(UserData::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun getJadwal(livedata: MutableLiveData<List<Jadwal>>) {
        val profileRef = dbRef.getReference("Users")
        val userId = firebaseAuth.currentUser!!.uid
        val jadwalRef = dbRef.getReference("kotler")

        profileRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.getValue(UserData::class.java)

                if (result != null) {
                    jadwalRef.child(result.kloter).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val jadwalItems: List<Jadwal> = snapshot.children.map {
                                it.getValue(Jadwal::class.java)!!
                            }

                            livedata.postValue(jadwalItems)
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    })
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }




}



