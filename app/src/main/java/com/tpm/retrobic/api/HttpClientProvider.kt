package com.tpm.retrobic.api

import com.tpm.retrobic.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClientProvider {
    fun provideOkHttpClient(): OkHttpClient
    {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10,TimeUnit.SECONDS)
        builder.connectTimeout(5,TimeUnit.SECONDS)
        if(BuildConfig.DEBUG)
        {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }
}