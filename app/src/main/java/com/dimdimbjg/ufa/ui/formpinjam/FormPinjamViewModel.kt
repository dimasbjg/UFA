package com.dimdimbjg.ufa.ui.formpinjam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.Peminjaman
import com.dimdimbjg.ufa.vo.Resource

class FormPinjamViewModel : ViewModel() {

    val repository = Repository()

    private val _peminjaman = MutableLiveData<Resource<Peminjaman>>()
    val peminjaman: LiveData<Resource<Peminjaman>> = _peminjaman

    fun sendPeminjaman(peminjaman: Peminjaman) {
        repository.sendPinjaman(_peminjaman, peminjaman)
    }

    fun deletePeminjaman(id: String) {
        repository.removePinjaman(id)
    }

}