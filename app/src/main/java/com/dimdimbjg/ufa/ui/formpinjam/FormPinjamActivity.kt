package com.dimdimbjg.ufa.ui.formpinjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityFormPinjamBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter

class FormPinjamActivity : AppCompatActivity() {

    private var items = arrayOf(
        "Koper",
        "Kursi Roda untuk Tawaf",
        "Kursi Roda untuk Sa'i",
        "Motor Listrik untuk Tawaf",
        "Motor Listrik untuk Sa'i"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFormPinjamBinding = ActivityFormPinjamBinding.inflate(layoutInflater)
        setContentView(activityFormPinjamBinding.root)

        val adapter = NoFilterArrayAdapter(this, R.layout.list_item,
            items)

        activityFormPinjamBinding.actvBarang.setAdapter(adapter)

    }
}