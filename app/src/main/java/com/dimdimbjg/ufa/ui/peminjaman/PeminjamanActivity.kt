package com.dimdimbjg.ufa.ui.peminjaman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimdimbjg.ufa.databinding.ActivityPeminjamanBinding
import com.dimdimbjg.ufa.ui.jadwal.JadwalAdapter
import com.dimdimbjg.ufa.vo.Status

class PeminjamanActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPeminjamanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[PeminjamanViewModel::class.java]

        viewModel.fetchData()
        val adapter = PeminjamanAdapter()
        binding.rvListPinjaman.layoutManager = LinearLayoutManager(this)
        binding.rvListPinjaman.adapter = adapter

        viewModel.peminjamanList.observe(this) {result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { adapter.setList(it) }
                }
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}