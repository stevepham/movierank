package com.ht117.movierank.listing


import com.ht117.movierank.Movie

import io.reactivex.Observable

/**
 * @author Quang Pham
 */
interface MoviesListingInteractor {
    fun fetchMovies(): Observable<List<Movie>>
}
