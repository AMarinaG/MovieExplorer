package com.amarinag.core.network.retrofit

import com.amarinag.core.network.model.NetworkMovie
import com.amarinag.core.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMovieNetworkApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NetworkResponse<List<NetworkMovie>>

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Int): NetworkMovie
}