package com.dimdimbjg.ufa.ui.formubah

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.data.source.network.PerubahanData
import com.dimdimbjg.ufa.data.source.network.UserData
import com.dimdimbjg.ufa.databinding.ActivityFormPerubahanBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter

class FormPerubahanActivity : AppCompatActivity() {

    private var items : Array<String>? = null

    private lateinit var viewModel: FormPerubahanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormPerubahanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items = resources.getStringArray(R.array.items_data_array)

        val adapterItems = NoFilterArrayAdapter(this, R.layout.list_item,
            items as Array<out String>)
        binding.actvItem.setAdapter(adapterItems)

        val userData = intent.getParcelableExtra<UserData>("USER_DATA")

        viewModel = ViewModelProvider(this)[FormPerubahanViewModel::class.java]

        binding.btConfirm.setOnClickListener {
            val kategori = binding.actvItem.text.toString().trim()
            val detail = binding.tvPenjelasan.text.toString().trim()
            if (kategori.isEmpty()) {
                binding.actvItem.error = "Harap pilih"
                binding.actvItem.requestFocus()
                return@setOnClickListener
            }

            if (detail.isEmpty()) {
                binding.tvPenjelasan.error = "Harap tuliskan detail dari perubahan yang diajukan"
                binding.tvPenjelasan.requestFocus()
                return@setOnClickListener
            }


            val perubahanData = PerubahanData(kategori, detail)

            if (userData != null) {
                Log.d("success", "data berhasil berubah")
                viewModel.sendPerubahan(perubahanData, userData)
            }

            finish()

        }

    }
}