package com.amarinag.core.data.repository

import com.amarinag.core.data.model.asDomain
import com.amarinag.core.data.model.asEntity
import com.amarinag.core.database.dao.MovieDao
import com.amarinag.core.database.model.MovieEntity
import com.amarinag.core.database.model.toDomain
import com.amarinag.core.model.Movie
import com.amarinag.core.network.MovieNetworkDataSource
import com.amarinag.core.network.model.NetworkMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnlineMovieRepository @Inject constructor(
    private val network: MovieNetworkDataSource,
    private val movieDao: MovieDao
) : MovieRepository {
    override fun getPlayNowMovies(): Flow<List<Movie>> = flow {
        emit(network.getNowPlayingMovies().map(NetworkMovie::asDomain))
    }

    override fun getMovieById(movieId: Int): Flow<Movie> = flow {
        emit(network.getMovieById(movieId).asDomain())
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        movieDao.getFavoriteMovies().map(List<MovieEntity>::toDomain)

    override suspend fun addFavorite(movie: Movie) = movieDao.addFavorite(movie.asEntity())

    override suspend fun deleteFavorite(movie: Movie) = movieDao.deleteFavorite(movie.asEntity())

}