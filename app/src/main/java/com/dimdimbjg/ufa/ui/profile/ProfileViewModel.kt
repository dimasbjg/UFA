package com.dimdimbjg.ufa.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimdimbjg.ufa.data.Repository
import com.dimdimbjg.ufa.data.source.network.UserDataResponse

class ProfileViewModel : ViewModel() {

    private val repository = Repository()

    private val _profile = MutableLiveData<UserDataResponse>()
    val profile = _profile

    fun fetchProfile() {
        repository.getProfile(_profile)
    }

}