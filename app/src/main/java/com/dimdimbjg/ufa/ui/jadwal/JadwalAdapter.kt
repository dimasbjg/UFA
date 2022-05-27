package com.dimdimbjg.ufa.ui.jadwal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimdimbjg.ufa.data.source.network.Jadwal
import com.dimdimbjg.ufa.databinding.PeminjamanItemBinding
import com.dimdimbjg.ufa.utils.DateConverter

class JadwalAdapter : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    private val jadwalItems = mutableListOf<Jadwal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val itemLayoutBinding =
            PeminjamanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JadwalViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val jadwal = jadwalItems[position]
        holder.onBind(jadwal)
    }

    override fun getItemCount(): Int {
        return jadwalItems.size
    }

    fun setJadwal(jadwalList: List<Jadwal>) {
        this.jadwalItems.clear()
        this.jadwalItems.addAll(jadwalList)
    }

    inner class JadwalViewHolder(private val binding: PeminjamanItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        private val dateConverter = DateConverter()

        fun onBind(jadwal: Jadwal) {
            binding.tvTitle.text = jadwal.kegiatan
            val waktu = jadwal.hari.replaceFirstChar { it.titlecase() } + " " + dateConverter.convertInttoDate(jadwal.tanggal) + " " + dateConverter.formattedTime(jadwal.pukul)
            binding.tvDescription.text = waktu
        }
    }


}