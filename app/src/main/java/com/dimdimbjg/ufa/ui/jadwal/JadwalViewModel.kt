package com.dimdimbjg.ufa.ui.jadwal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.Jadwal

class JadwalViewModel : ViewModel() {

    private val repository = Repository()

    private val _jadwal = MutableLiveData<List<Jadwal>>()
    val jadwal = _jadwal

    fun fetchJadwal() {
        repository.getJadwal(_jadwal)
        Log.d("Berhasil", jadwal.value?.get(0)?.hari.toString())
    }

}