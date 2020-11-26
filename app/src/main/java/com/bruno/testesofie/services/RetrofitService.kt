package com.bruno.testesofie.services

import com.bruno.testesofie.config.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Urls().baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> cteateService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass!!)
    }
}