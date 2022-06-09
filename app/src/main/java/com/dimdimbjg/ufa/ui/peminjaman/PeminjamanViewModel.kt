package com.dimdimbjg.ufa.ui.peminjaman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.Peminjaman
import com.dimdimbjg.ufa.vo.Resource

class PeminjamanViewModel : ViewModel() {

    val repository = Repository()

    private val _peminjamanList = MutableLiveData<Resource<List<Peminjaman>>>()
    val peminjamanList: LiveData<Resource<List<Peminjaman>>> = _peminjamanList

    fun fetchData() {
        repository.getPeminjamanList(_peminjamanList)
    }

}