package com.amarinag.core.network.retrofit

import com.amarinag.core.network.model.NetworkMovie
import com.amarinag.core.network.model.NetworkResponse
import retrofit2.http.GET

interface RetrofitMovieNetworkApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NetworkResponse<List<NetworkMovie>>
}