package com.dimdimbjg.ufa.ui.peminjaman

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimdimbjg.ufa.data.source.network.Peminjaman
import com.dimdimbjg.ufa.databinding.PeminjamanItemBinding

class PeminjamanAdapter : RecyclerView.Adapter<PeminjamanAdapter.PeminjamanViewHolder>() {

    val peminjamanItems = mutableListOf<Peminjaman>()

    inner class PeminjamanViewHolder(private val binding: PeminjamanItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun onBind(peminjaman: Peminjaman) {
            binding.tvTitle.text = peminjaman.barang
            if (peminjaman.verified) {
                "${peminjaman.barang}\nSebanyak ${peminjaman.jumlah}\nUntuk ${peminjaman.untuk}\nStatus sudah terverifikasi".also {
                    binding.tvDescription.text = it
                }
            } else {
                "${peminjaman.barang}\nSebanyak ${peminjaman.jumlah}\nUntuk ${peminjaman.untuk}\nStatus belom terverifikasi".also {
                    binding.tvDescription.text = it
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeminjamanViewHolder {
        val itemLayoutBinding =
            PeminjamanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeminjamanViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: PeminjamanViewHolder, position: Int) {
        val peminjaman = peminjamanItems[position]
        holder.onBind(peminjaman)
    }

    override fun getItemCount(): Int = peminjamanItems.size
}