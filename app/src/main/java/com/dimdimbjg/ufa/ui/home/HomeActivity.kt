package com.dimdimbjg.ufa.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityHomeBinding
import com.dimdimbjg.ufa.ui.informasi.InformasiActivity
import com.dimdimbjg.ufa.ui.jadwal.JadwalActivity
import com.dimdimbjg.ufa.ui.login.LoginActivity
import com.dimdimbjg.ufa.ui.peminjaman.PeminjamanActivity
import com.dimdimbjg.ufa.ui.profile.ProfileActivity
import com.dimdimbjg.ufa.utils.CheckNetworkConnection
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callNetworkAvailability()

        binding.cardProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.cardJadwal.setOnClickListener {
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
        }

        binding.imgLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.cardInformasi.setOnClickListener {
            val intent = Intent(this, InformasiActivity::class.java)
            startActivity(intent)
        }

        binding.cardPeminjaman.setOnClickListener {
            val intent = Intent(this, PeminjamanActivity::class.java)
            startActivity(intent)
        }

    }

    private fun callNetworkAvailability() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                Toast.makeText(this, getString(R.string.connection_normal), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, getString(R.string.error_no_connection), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            finish()
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tekan kembali 2 kali untuk keluar", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}