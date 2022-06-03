package com.dimdimbjg.ufa.ui.formubah

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.PerubahanData
import com.dimdimbjg.ufa.data.source.network.UserData
import com.dimdimbjg.ufa.vo.Resource

class FormPerubahanViewModel : ViewModel() {

    private val repository = Repository()

    private val _perubahan = MutableLiveData<Resource<PerubahanData>>()
    val perubahan = _perubahan

    fun sendPerubahan(perubahanData: PerubahanData,
                      userData: UserData) {
        repository.sendPerubahan(perubahanData, userData, _perubahan)
    }

}