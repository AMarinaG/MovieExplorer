package com.amarinag.core.network

import com.amarinag.core.network.model.NetworkMovie

interface MovieNetworkDataSource {
    suspend fun getNowPlayingMovies(): List<NetworkMovie>
    suspend fun getMovieById(movieId: Int): NetworkMovie
}