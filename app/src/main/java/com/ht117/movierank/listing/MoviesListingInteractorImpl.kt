package com.ht117.movierank.listing

import com.ht117.movierank.model.Movie
import com.ht117.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.listing.sorting.SortType
import com.ht117.movierank.listing.sorting.SortingOptionStore
import com.ht117.movierank.network.TmdbWebService
import io.reactivex.Observable

/**
 * @author Quang Pham
 */
internal class MoviesListingInteractorImpl(private val favoritesInteractor: FavoritesInteractor,
                                           private val service: TmdbWebService,
                                           private val sortingOptionStore: SortingOptionStore) : MoviesListingInteractor {

    override fun fetchMovies(): Observable<List<Movie>> {
        val selectedOption = sortingOptionStore.selectedOption
        return when(selectedOption) {
            SortType.MOST_POPULAR.value -> service.popularMovies().map { it.movieList }
            SortType.HIGHEST_RATED.value -> service.highestRatedMovies().map { it.movieList }
            else -> Observable.just(favoritesInteractor.favorites)
        }
    }

}
