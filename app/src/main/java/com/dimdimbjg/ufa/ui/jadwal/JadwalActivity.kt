package com.dimdimbjg.ufa.ui.jadwal

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimdimbjg.ufa.data.source.network.Jadwal
import com.dimdimbjg.ufa.databinding.ActivityJadwalBinding
import com.dimdimbjg.ufa.utils.CurrentDate
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

        val adapter2 = JadwalAdapter()
        binding.rvSemuaJadwal.layoutManager = LinearLayoutManager(this)
        binding.rvSemuaJadwal.adapter = adapter2

        viewModel.jadwal.observe(this) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let {
                        //JADWAL TERDEKAT
                        val listTerdekat = mutableListOf<Jadwal>()
                        for (jadwal in it) {
                            if (listTerdekat.size < 3) {
                                if (jadwal.tanggal > CurrentDate.getDayInformat()) {
                                    listTerdekat.add(jadwal)
                                }
                            }
                        }
                        if (listTerdekat.size == 0) {
                            binding.tvJadwalKosong.visibility = View.VISIBLE
                        }
                        adapter.setJadwal(listTerdekat)
                        adapter.notifyDataSetChanged()

                        //SEMUA JADWAL
                        adapter2.setJadwal(it)
                        adapter2.notifyDataSetChanged()

                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}