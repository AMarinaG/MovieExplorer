package com.amarinag.core.network.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LangInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter("language", "es-ES").build()
        val newRequest = request.newBuilder().url(newUrl)
        return chain.proceed(newRequest.build())
    }
}