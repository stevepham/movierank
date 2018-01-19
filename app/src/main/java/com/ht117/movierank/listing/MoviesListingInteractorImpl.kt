package com.ht117.movierank.listing

import com.ht117.movierank.Movie
import com.ht117.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.listing.sorting.SortType
import com.ht117.movierank.listing.sorting.SortingOptionStore
import com.ht117.movierank.network.TmdbWebService
import io.reactivex.Observable

/**
 * @author Quang Pham
 */
internal class MoviesListingInteractorImpl(val favoritesInteractor: FavoritesInteractor,
                                           val tmdbWebService: TmdbWebService,
                                           val sortingOptionStore: SortingOptionStore) : MoviesListingInteractor {

    override fun fetchMovies(): Observable<List<Movie>> {
        val selectedOption = sortingOptionStore.selectedOption
        if (selectedOption == SortType.MOST_POPULAR.value) {
            return tmdbWebService.popularMovies().map({ it.movieList })
        } else return if (selectedOption == SortType.HIGHEST_RATED.value) {
            tmdbWebService.highestRatedMovies().map({ it.movieList })
        } else {
            Observable.just(favoritesInteractor.favorites)
        }
    }

}
