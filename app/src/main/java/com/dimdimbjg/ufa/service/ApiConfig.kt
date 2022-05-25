package com.dimdimbjg.ufa.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        fun getInstance(): Retrofit {
            val client = OkHttpClient.Builder()
                .build()
            return retrofit ?: synchronized(this) {
                retrofit ?: Retrofit.Builder()
                    .baseUrl("https://umroh-farfasa-apps-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
        }

    }
}