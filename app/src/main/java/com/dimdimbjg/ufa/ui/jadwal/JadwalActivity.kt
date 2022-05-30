package com.dimdimbjg.ufa.ui.jadwal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityJadwalBinding
import com.dimdimbjg.ufa.vo.Status

class JadwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[JadwalViewModel::class.java]

        viewModel.fetchJadwal()
        val adapter = JadwalAdapter()
        binding.rvJadwalTerdekat.layoutManager = LinearLayoutManager(this)
        binding.rvJadwalTerdekat.adapter = adapter

        viewModel.jadwal.observe(this) {result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { adapter.setJadwal(it) }
                }
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}