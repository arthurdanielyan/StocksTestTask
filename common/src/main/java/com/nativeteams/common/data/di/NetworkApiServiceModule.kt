package com.nativeteams.common.data.di

import com.nativeteams.common.data.network.NetworkApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkApiServiceModule {

    @Provides
    @Singleton
    fun provideNetworkApiService(): NetworkApiService {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .addHeader("X-RapidAPI-Key", "b8e311f8e2msh45bf0afc03715fcp11d5b7jsn7c59bca5776f")
                .addHeader("X-RapidAPI-Host", "yh-finance.p.rapidapi.com")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        val client = httpClient.build()
        return Retrofit.Builder()
            .baseUrl("https://yh-finance.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NetworkApiService::class.java)
    }
}