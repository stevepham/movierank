package com.ht117.movierank.listing

import com.ht117.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.listing.sorting.SortingOptionStore
import com.ht117.movierank.network.TmdbWebService
import dagger.Module
import dagger.Provides

/**
 * @author Quang Pham
 */
@Module
class ListingModule {
    @Provides
    internal fun provideMovieListingInteractor(favoritesInteractor: FavoritesInteractor,
                                               tmdbWebService: TmdbWebService,
                                               sortingOptionStore: SortingOptionStore): MoviesListingInteractor {
        return MoviesListingInteractorImpl(favoritesInteractor, tmdbWebService, sortingOptionStore)
    }

    @Provides
    internal fun provideMovieListingPresenter(interactor: MoviesListingInteractor): MoviesListingPresenter {
        return MoviesListingPresenterImpl(interactor)
    }
}
