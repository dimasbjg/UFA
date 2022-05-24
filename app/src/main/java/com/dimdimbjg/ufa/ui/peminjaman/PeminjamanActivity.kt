package com.dimdimbjg.ufa.ui.peminjaman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityPeminjamanBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter

class PeminjamanActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityPeminjamanBinding = ActivityPeminjamanBinding.inflate(layoutInflater)
        setContentView(activityPeminjamanBinding.root)


    }
}