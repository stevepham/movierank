package com.ht117.movierank.details

import com.ht117.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.network.TmdbWebService

import dagger.Module
import dagger.Provides

/**
 * @author Quang Pham
 */
@Module
class DetailsModule {
    @Provides
    @DetailsScope
    internal fun provideInteractor(tmdbWebService: TmdbWebService): MovieDetailsInteractor {
        return MovieDetailsInteractorImpl(tmdbWebService)
    }

    @Provides
    @DetailsScope
    internal fun providePresenter(detailsInteractor: MovieDetailsInteractor,
                                  favoritesInteractor: FavoritesInteractor): MovieDetailsPresenter {
        return MovieDetailsPresenterImpl(detailsInteractor, favoritesInteractor)
    }
}
