package com.khilman.www.formchecklistapp.network

import android.os.Build.VERSION_CODES.BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InitRetrofit {
    companion object {
        val API_BASE = "http://10.0.2.2:8000/"
        val API_BASE_URL = API_BASE + "api/";
        val API_BASE_IMAGES = API_BASE + "product_images/";
        val API_BASE_DOCUMENT = API_BASE + "document/";
        val API_BASE_PHOTO_PROFILE = API_BASE + "user_photo/";
    }

    fun getInit(): Retrofit {
        return Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): ApiServices {
        return getInit().create(ApiServices::class.java)
    }
}