package com.amarinag.core.network

import com.amarinag.core.network.retrofit.interceptor.AuthInterceptor
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthInterceptorTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockChain: Interceptor.Chain

    @MockK
    private lateinit var mockRequest: Request

    @MockK
    private lateinit var mockNewRequestBuilder: Request.Builder

    @MockK
    private lateinit var mockNewRequest: Request

    @MockK
    private lateinit var mockResponse: Response


    private lateinit var authInterceptor: AuthInterceptor

    @Before
    fun setup() {
        authInterceptor = AuthInterceptor()

        every { mockChain.request() } returns mockRequest
        every { mockRequest.newBuilder() } returns mockNewRequestBuilder
        every { mockNewRequestBuilder.addHeader(any(), any()) } returns mockNewRequestBuilder
        every { mockNewRequestBuilder.build() } returns mockNewRequest
        every { mockChain.proceed(mockNewRequest) } returns mockResponse
    }

    @Test
    fun `test intercept adds Authorization header`() {

        val result = authInterceptor.intercept(mockChain)

        assertThat(result).isEqualTo(mockResponse)
        verify(exactly = 1) {
            mockNewRequestBuilder.addHeader(
                "Authorization",
                "Bearer ${BuildConfig.ACCESS_TOKEN_AUTH}"
            )
            mockNewRequestBuilder.build()
        }
        verify(exactly = 1) { mockChain.proceed(mockNewRequest) }
    }
}