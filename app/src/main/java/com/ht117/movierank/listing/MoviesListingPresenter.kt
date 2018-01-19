package com.ht117.movierank.listing

/**
 * @author Quang Pham
 */
interface MoviesListingPresenter {
    fun displayMovies()

    fun setView(view: MoviesListingView)

    fun destroy()
}
