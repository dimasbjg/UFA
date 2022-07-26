package com.dimdimbjg.ufa.ui.formpinjam

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.data.source.network.Peminjaman
import com.dimdimbjg.ufa.data.source.network.Status
import com.dimdimbjg.ufa.databinding.ActivityFormPinjamBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter


class FormPinjamActivity : AppCompatActivity() {

    private var items = arrayOf(
        "Koper",
        "Kursi Roda untuk Tawaf",
        "Kursi Roda untuk Sa'i",
        "Motor Listrik untuk Tawaf",
        "Motor Listrik untuk Sa'i"
    )

    private lateinit var adapter: NoFilterArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormPinjamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[FormPinjamViewModel::class.java]

        adapter = NoFilterArrayAdapter(this, R.layout.list_item,
            items)

        binding.actvBarang.setAdapter(adapter)

        val data = intent.getParcelableExtra<Peminjaman>("PEMINJAMAN")

        if (data != null) {
            binding.actvBarang.threshold = Integer.MAX_VALUE
            binding.actvBarang.setText(data.barang, false)
            binding.actvBarang.threshold = 1
            binding.actvBarang.dropDownHeight = 0

            if (data.untuk == "Diri Sendiri") {
                binding.rbDiriSendiri.isChecked = true
                binding.rbOrangLain.isChecked = false

                binding.rbDiriSendiri.isClickable = false
                binding.rbOrangLain.isClickable = false
            } else {
                binding.rbDiriSendiri.isChecked = false
                binding.rbOrangLain.isChecked = true

                binding.rbDiriSendiri.isClickable = false
                binding.rbOrangLain.isClickable = false
            }
            if (data.verified) {
                binding.btPinjam.visibility = View.GONE
                binding.btBatal.visibility = View.GONE
            } else {
                binding.btPinjam.visibility = View.GONE
            }
        } else {
            binding.btBatal.visibility = View.GONE
        }

        binding.btPinjam.setOnClickListener {

            if (binding.actvBarang.text.toString().isEmpty()) {
                Toast.makeText(this, "Harap isi barang yang akan dipinjam", Toast.LENGTH_SHORT).show()
                binding.actvBarang.requestFocus()
                return@setOnClickListener
            } else {
                if(binding.radioGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(this, "Harap pilih pengguna barang pinjaman", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    val selectedId = binding.radioGroup.checkedRadioButtonId
                    val radioButton: RadioButton = findViewById(selectedId)
                    val status = Status()
                    val peminjaman = Peminjaman(
                        barang = binding.actvBarang.text.toString(),
                        jumlah = 1,
                        status = status,
                        untuk = radioButton.text.toString(),
                        verified = false
                    )
                    viewModel.sendPeminjaman(peminjaman)
                    finish()
                }
            }

        }

        binding.btBatal.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("Verifikasi pembatalan peminjaman")
                .setMessage("Apakah anda yakin untuk membatalkan permintaan peminjaman?")
                .setPositiveButton("Iya") { dialog, id ->
                    if (data != null) {
                        viewModel.deletePeminjaman(data.id)
                        finish()
                    }
                    dialog.cancel()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.cancel()
                }

            val alert: AlertDialog = builder.create()
            alert.show()
        }
    }

    private fun findIndex(arr: Array<String>, item: String): Int? {
        return (arr.indices)
            .firstOrNull { i: Int -> item == arr[i] }
    }
}