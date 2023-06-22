package com.amarinag.core.data.model

import com.amarinag.core.model.Movie
import com.amarinag.core.network.model.NetworkMovie

fun NetworkMovie.asDomain(): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    genreIds = genreIds,
    popularity = popularity,
    releaseDate = releaseDate,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)