package com.dimdimbjg.ufa.ui.informasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimdimbjg.ufa.data.source.network.Informasi
import com.dimdimbjg.ufa.databinding.PeminjamanItemBinding

class InformasiAdapter : RecyclerView.Adapter<InformasiAdapter.InformasiViewHolder>() {

    private val informasiList = mutableListOf<Informasi>()

    inner class InformasiViewHolder(private val binding: PeminjamanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(informasi: Informasi) {
            binding.tvTitle.text = informasi.judul
            binding.tvDescription.text = informasi.isi
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformasiViewHolder {
        val itemLayoutBinding =
            PeminjamanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InformasiViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: InformasiViewHolder, position: Int) {
        val informasi = informasiList[position]
        holder.onBind(informasi)
    }

    override fun getItemCount(): Int = informasiList.size
}