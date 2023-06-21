package com.amarinag.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.core.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel : ViewModel() {
    val favoriteUiState: StateFlow<FavoriteUiState> = flow {
        delay(2000)
        emit(FavoriteUiState.Success(movies))
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoriteUiState.Loading
        )
}

private val movies = listOf<Movie>(
    Movie(
        adult = false,
        backdropPath = "/wRxLAw4l17LqiFcPLkobriPTZAw.jpg",
        id = 697843,
        title = "Extraction 2",
        originalLanguage = "en",
        originalTitle = "Extraction 2",
        overview = "Tasked with extracting a family who is at the mercy of a Georgian gangster, Tyler Rake infiltrates one of the world's deadliest prisons in order to save them. But when the extraction gets hot, and the gangster dies in the heat of battle, his equally ruthless brother tracks down Rake and his team to Sydney, in order to get revenge.",
        posterPath = "/7gKI9hpEMcZUQpNgKrkDzJpbnNS.jpg",
        mediaType = "movie",
        genreIds = listOf(28, 53),
        popularity = 1180.988,
        releaseDate = "2023-06-12",
        video = false,
        voteAverage = 7.858,
        voteCount = 537
    ),
    Movie(
        adult = false,
        backdropPath = "/7e9MVGg8efOhoA2R9XhZcGWTC5Z.jpg",
        id = 298618,
        title = "The Flash",
        originalLanguage = "en",
        originalTitle = "The Flash",
        overview = "When his attempt to save his family inadvertently alters the future, Barry Allen becomes trapped in a reality in which General Zod has returned and there are no Super Heroes to turn to. In order to save the world that he is in and return to the future that he knows, Barry's only hope is to race for his life. But will making the ultimate sacrifice be enough to reset the universe?",
        posterPath = "/rktDFPbfHfUbArZ6OOOKsXcv0Bm.jpg",
        mediaType = "movie",
        genreIds = listOf(878, 28, 12),
        popularity = 1732.233,
        releaseDate = "2023-06-14",
        video = false,
        voteAverage = 6.806,
        voteCount = 427
    ),
    Movie(
        adult = false,
        backdropPath = "/6l1SV3CWkbbe0DcAK1lyOG8aZ4K.jpg",
        id = 385687,
        title = "Fast X",
        originalLanguage = "en",
        originalTitle = "Fast X",
        overview = "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.",
        posterPath = "/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
        mediaType = "movie",
        genreIds = listOf(28, 80, 53),
        popularity = 8893.249,
        releaseDate = "2023-05-17",
        video = false,
        voteAverage = 7.345,
        voteCount = 1812
    ),
    Movie(
        adult = false,
        backdropPath = "/2I5eBh98Q4aPq8WdQrHdTC8ARhY.jpg",
        id = 569094,
        title = "Spider-Man: Across the Spider-Verse",
        originalLanguage = "en",
        originalTitle = "Spider-Man: Across the Spider-Verse",
        overview = "After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse’s very existence. But when the heroes clash on how to handle a new threat, Miles finds himself pitted against the other Spiders and must set out on his own to save those he loves most.",
        posterPath = "/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg",
        mediaType = "movie",
        genreIds = listOf(28, 12, 16, 878),
        popularity = 2267.849,
        releaseDate = "2023-05-31",
        video = false,
        voteAverage = 8.688,
        voteCount = 1525
    ),
    Movie(
        adult = false,
        backdropPath = "/vnPTLSBk95XKdahOaMkTlAck5Rc.jpg",
        id = 882569,
        title = "Guy Ritchie's The Covenant",
        originalLanguage = "en",
        originalTitle = "Guy Ritchie's The Covenant",
        overview = "During the war in Afghanistan, a local interpreter risks his own life to carry an injured sergeant across miles of grueling terrain.",
        posterPath = "/kVG8zFFYrpyYLoHChuEeOGAd6Ru.jpg",
        mediaType = "movie",
        genreIds = listOf(10752, 28, 53),
        popularity = 990.636,
        releaseDate = "2023-04-19",
        video = false,
        voteAverage = 7.63,
        voteCount = 593
    ),
)