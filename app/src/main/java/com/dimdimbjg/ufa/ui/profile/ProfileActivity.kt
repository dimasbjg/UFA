package com.dimdimbjg.ufa.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dimdimbjg.ufa.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.fetchProfile()
        viewModel.profile.observe(this) { profile ->
            binding.textNamaLengkap.text = profile.nama
        }


//        if (result != null) {
//            let {
//                binding.textNamaLengkap.text = result.nama
//                binding.textKewarganegaraanField.text = result.kewarganegaraan
//                binding.textJenisKelaminField.text =
//                    if (result.kelamin == true) "laki-laki" else "perempuan"
//                binding.textAlamatField.text = result.alamat
//                binding.textNikField.text = result.nik.toString()
//                Log.d("Tidak null", "Horee")
//            }
//        } else {
//            Log.d("gagal", "terima null")
//        }


    }
}