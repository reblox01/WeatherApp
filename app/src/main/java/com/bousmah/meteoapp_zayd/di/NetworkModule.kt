package com.bousmah.meteoapp_zayd.di

import com.bousmah.meteoapp_zayd.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // OWASP: Certificate Pinning for security
        val certificatePinner = CertificatePinner.Builder()
            .add("api.openweathermap.org", "sha256/axmG9ZzpvYf9Sct9I9KzZk1Bdfy9Uay7N0n6f3pC2vM=") // Example hash, should be updated with actual current hash
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(okHttpClient: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
