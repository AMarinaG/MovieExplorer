package com.amarinag.core.data.repository

import com.amarinag.core.data.model.asDomain
import com.amarinag.core.model.Movie
import com.amarinag.core.network.MovieNetworkDataSource
import com.amarinag.core.network.model.NetworkMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnlineMovieRepository @Inject constructor(
    private val network: MovieNetworkDataSource
) : MovieRepository {
    override fun getPlayNowMovies(): Flow<List<Movie>> = flow {
        emit(network.getNowPlayingMovies().map(NetworkMovie::asDomain))
    }

}