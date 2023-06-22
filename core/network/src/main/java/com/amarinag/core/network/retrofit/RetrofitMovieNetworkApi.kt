package com.amarinag.core.network.retrofit

import com.amarinag.core.network.model.NetworkMovie
import com.amarinag.core.network.model.NetworkResponse

interface RetrofitMovieNetworkApi {
    suspend fun getNowPlayingMovies(): NetworkResponse<List<NetworkMovie>>
}