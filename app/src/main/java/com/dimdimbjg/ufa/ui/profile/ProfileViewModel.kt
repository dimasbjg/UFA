package com.dimdimbjg.ufa.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.UserData
import com.dimdimbjg.ufa.vo.Resource

class ProfileViewModel : ViewModel() {

    private val repository = Repository()

    private val _profile = MutableLiveData<Resource<UserData>>()
    val profile: LiveData<Resource<UserData>> = _profile

    fun fetchProfile() {
        repository.getProfile(_profile)
    }

    fun updateVerfifyRequestProfile() {
        _profile.value?.data?.requestverify   = true
        repository.updateProfile(_profile.value!!.data!!, _profile)
    }

}