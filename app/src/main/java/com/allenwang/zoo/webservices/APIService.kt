package com.allenwang.zoo.webservices

import com.allenwang.zoo.pojo.PlantBase
import com.allenwang.zoo.pojo.ParkBase
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Retrofit services
 */
interface APIService {
    @GET("apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getParks(): Observable<ParkBase>

    @GET("apiAccess?scope=resourceAquire&rid=f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlants(): Observable<PlantBase>

    companion object {
        fun create(): APIService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://data.taipei/opendata/datalist/")
                .client(httpClient.build())
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}