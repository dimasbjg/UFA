package com.dimdimbjg.ufa.ui.peminjaman

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimdimbjg.ufa.data.source.network.Peminjaman
import com.dimdimbjg.ufa.databinding.ActivityPeminjamanBinding
import com.dimdimbjg.ufa.ui.formpinjam.FormPinjamActivity
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

        viewModel.peminjamanList.observe(this) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let {
                        adapter.setList(it)
                        Log.d("berhasil" , it.toString())
                        adapter.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        adapter.onItemClick = {
            val intent = Intent(this, FormPinjamActivity::class.java)
            intent.putExtra("PEMINJAMAN", it)
            startActivity(intent)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, FormPinjamActivity::class.java)
            startActivity(intent)
        }

    }

}