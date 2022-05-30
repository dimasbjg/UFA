package com.dimdimbjg.ufa.ui.informasi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimdimbjg.ufa.databinding.ActivityInformasiBinding
import com.dimdimbjg.ufa.vo.Status

class InformasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInformasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[InformasiViewModel::class.java]

        viewModel.fetchInformasiList()

        val adapter = InformasiAdapter()
        binding.rvListPinjaman.layoutManager = LinearLayoutManager(this)
        binding.rvListPinjaman.adapter = adapter

        viewModel.informasiList.observe(this) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { adapter.setInformasiList(result.data) }
                    adapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

//        viewModel.informasiList.observe(this) {informasi ->
//            adapter.setInformasiList(informasi)
//            adapter.notifyDataSetChanged()
//        }

    }
}