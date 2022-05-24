package com.dimdimbjg.ufa.ui.formubah

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityFormPerubahanBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter

class FormPerubahanActivity : AppCompatActivity() {

    private var items = arrayOf("Nama Lengkap","NIK","Jenis Kelamin","Tempat Lahir","Tanggal Lahir","Status Menikah","Orang Tua","Alamat","Lainnya")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFormPerubahanBinding = ActivityFormPerubahanBinding.inflate(layoutInflater)
        setContentView(activityFormPerubahanBinding.root)

        activityFormPerubahanBinding.actvItem.freezesText = false

        val adapterItems = NoFilterArrayAdapter(this, R.layout.list_item, items)
        activityFormPerubahanBinding.actvItem.setAdapter(adapterItems)
    }
}