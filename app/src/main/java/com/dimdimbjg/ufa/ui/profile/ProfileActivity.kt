package com.dimdimbjg.ufa.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityProfileBinding
import com.dimdimbjg.ufa.ui.formubah.FormPerubahanActivity
import com.dimdimbjg.ufa.vo.Status

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
            binding.textNikField.visibility = it
            binding.textJenisKelaminField.visibility = it
            binding.textKewarganegaraanField.visibility = it
            binding.textTanggalLahirField.visibility = it
            binding.textStatusMenikahField.visibility = it
            binding.textTempatLahirField.visibility = it
            binding.textAlamatField.visibility = it
            binding.textOrangTuaField.visibility = it

            binding.textNik.visibility = it
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

        viewModel.profile.observe(this) { result ->

            when (result.status) {
                Status.SUCCESS -> {
                    if (result.data != null) {
                        binding.textNamaLengkap.text = result.data.nama
                        binding.textNikField.text = result.data.nik.toString()
                        binding.textJenisKelaminField.text =
                            if (result.data.kelamin) "Laki - Laki" else "Perempuan"
                        binding.textKewarganegaraanField.text = result.data.kewarganegaraan
                        binding.textTanggalLahirField.text = result.data.tanggallahir.toString()
                        binding.textStatusMenikahField.text =
                            if (result.data.menikah) "Sudah Menikah" else "Belom Menikah"
                        binding.textTempatLahirField.text = result.data.tempatlahir
                        binding.textAlamatField.text = result.data.alamat
                        binding.textOrangTuaField.text =
                            resources.getString(R.string.nama_orang_tua,
                                result.data.orangtua1,
                                result.data.orangtua2)
                        View.VISIBLE.let {
                            binding.textNamaLengkap.visibility = it
                            binding.textNikField.visibility = it
                            binding.textJenisKelaminField.visibility = it
                            binding.textKewarganegaraanField.visibility = it
                            binding.textTanggalLahirField.visibility = it
                            binding.textStatusMenikahField.visibility = it
                            binding.textTempatLahirField.visibility = it
                            binding.textAlamatField.visibility = it
                            binding.textOrangTuaField.visibility = it

                            binding.textNik.visibility = it
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
                Status.ERROR -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btVerifikasi.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("Verifikasi kebenaran data")
                .setMessage("Apakah anda sudah yakin data yang tertera sudah benar dengan data sesungguhnya dan menyetujui verifikasi data dilakukan oleh sistem?")
                .setPositiveButton("Iya") { dialog, id ->
                    // yes todo
                    dialog.cancel()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.cancel()
                }

            val alert: AlertDialog = builder.create()
            alert.show()
        }

        binding.btAjaukanPerubahan.setOnClickListener {
            val intent = Intent(this, FormPerubahanActivity::class.java)
            startActivity(intent)
        }

    }
}