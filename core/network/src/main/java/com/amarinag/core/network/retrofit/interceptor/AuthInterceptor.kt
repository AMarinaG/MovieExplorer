package com.amarinag.core.network.retrofit.interceptor

import com.amarinag.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN_AUTH}")
        return chain.proceed(newRequest.build())
    }
}