package com.dimdimbjg.ufa.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.UserData

class ProfileViewModel : ViewModel() {

    private val repository = Repository()

    private val _profile = MutableLiveData<UserData>()
    val profile = _profile

    fun fetchProfile() {
        repository.getProfile(_profile)
    }

}