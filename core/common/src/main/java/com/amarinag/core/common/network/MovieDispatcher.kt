package com.amarinag.core.common.network

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val movieDispatcher: MovieDispatchers)

enum class MovieDispatchers {
    Default,
    IO,
}
