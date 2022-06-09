package com.dimdimbjg.ufa.ui.informasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.Informasi
import com.dimdimbjg.ufa.vo.Resource

class InformasiViewModel : ViewModel() {

    val repository = Repository()

    private val _informasiList = MutableLiveData<Resource<List<Informasi>>>()
    val informasiList: LiveData<Resource<List<Informasi>>> = _informasiList

    fun fetchInformasiList() {
        repository.getInformasi(_informasiList)
    }

}