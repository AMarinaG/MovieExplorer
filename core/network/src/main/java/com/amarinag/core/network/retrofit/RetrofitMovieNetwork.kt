package com.amarinag.core.network.retrofit

import com.amarinag.core.network.MovieNetworkDataSource
import com.amarinag.core.network.model.NetworkMovie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitMovieNetwork @Inject constructor(
    private val networkApi: RetrofitMovieNetworkApi
) : MovieNetworkDataSource {
    override suspend fun getNowPlayingMovies(): List<NetworkMovie> =
        networkApi.getNowPlayingMovies().results
}