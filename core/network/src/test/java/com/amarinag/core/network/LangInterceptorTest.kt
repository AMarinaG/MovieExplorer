package com.amarinag.core.network

import com.amarinag.core.network.retrofit.interceptor.LangInterceptor
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LangInterceptorTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockChain: Interceptor.Chain

    @MockK
    private lateinit var mockRequest: Request
    @MockK
    private lateinit var mockkNewUrl: HttpUrl

    @MockK
    private lateinit var mockNewRequestBuilder: Request.Builder

    @MockK
    private lateinit var mockNewRequest: Request

    @MockK
    private lateinit var mockResponse: Response
    private lateinit var langInterceptor: LangInterceptor

    @Before
    fun setup() {
        langInterceptor = LangInterceptor()
        every { mockChain.request() } returns mockRequest

        every { mockRequest.url.newBuilder().addQueryParameter(any(), any()).build() } returns mockkNewUrl
        every { mockRequest.newBuilder().url(mockkNewUrl) } returns mockNewRequestBuilder
        every { mockNewRequestBuilder.build() } returns mockNewRequest
        every { mockChain.proceed(mockNewRequest) } returns mockResponse

    }


    @Test
    fun `test intercept adds language query parameter`() {

        langInterceptor.intercept(mockChain)
        verify { mockChain.request() }
        verify { mockRequest.url.newBuilder().addQueryParameter("language", "es-ES") }
        verify { mockRequest.newBuilder().url(mockkNewUrl) }
        verify { mockChain.proceed(mockNewRequest) }
    }
}