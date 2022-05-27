package com.dimdimbjg.ufa.ui.profile

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]


        View.GONE.let {
            binding.textNamaLengkap.visibility = it
            binding.textNikField.visibility =it
            binding.textJenisKelaminField.visibility = it
            binding.textKewarganegaraanField.visibility = it
            binding.textTanggalLahirField.visibility = it
            binding.textStatusMenikahField.visibility = it
            binding.textTempatLahirField.visibility = it
            binding.textAlamatField.visibility = it
            binding.textOrangTuaField.visibility = it

            binding.textNik.visibility =it
            binding.textJenisKelamin.visibility = it
            binding.textKewarganegaraan.visibility = it
            binding.textTanggalLahir.visibility = it
            binding.textStatusMenikah.visibility = it
            binding.textTempatLahir.visibility = it
            binding.textAlamat.visibility = it
            binding.textOrangTua.visibility = it
            binding.progressBar.visibility = View.VISIBLE
        }

        viewModel.fetchProfile()
        viewModel.profile.observe(this) { profile ->

            binding.textNamaLengkap.text = profile.nama
            binding.textNikField.text = profile.nik.toString()
            binding.textJenisKelaminField.text = if (profile.kelamin) "Laki - Laki" else "Perempuan"
            binding.textKewarganegaraanField.text = profile.kewarganegaraan
            binding.textTanggalLahirField.text = profile.tanggallahir.toString()
            binding.textStatusMenikahField.text = if (profile.menikah) "Sudah Menikah" else "Belom Menikah"
            binding.textTempatLahirField.text = profile.tempatlahir
            binding.textAlamatField.text = profile.alamat
            binding.textOrangTuaField.text = resources.getString(R.string.nama_orang_tua,profile.orangtua1,profile.orangtua2)
            View.VISIBLE.let {
                binding.textNamaLengkap.visibility = it
                binding.textNikField.visibility =it
                binding.textJenisKelaminField.visibility = it
                binding.textKewarganegaraanField.visibility = it
                binding.textTanggalLahirField.visibility = it
                binding.textStatusMenikahField.visibility = it
                binding.textTempatLahirField.visibility = it
                binding.textAlamatField.visibility = it
                binding.textOrangTuaField.visibility = it

                binding.textNik.visibility =it
                binding.textJenisKelamin.visibility = it
                binding.textKewarganegaraan.visibility = it
                binding.textTanggalLahir.visibility = it
                binding.textStatusMenikah.visibility = it
                binding.textTempatLahir.visibility = it
                binding.textAlamat.visibility = it
                binding.textOrangTua.visibility = it
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}