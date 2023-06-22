package com.amarinag.core.data.di

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.data.repository.OnlineMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsMovieRepository(movieRepository: OnlineMovieRepository): MovieRepository
}