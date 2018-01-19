package com.ht117.movierank.listing


import com.ht117.movierank.Movie

/**
 * @author Quang Pham
 */
interface MoviesListingView {
    fun showMovies(movies: List<Movie>)
    fun loadingStarted()
    fun loadingFailed(errorMessage: String)
    fun onMovieClicked(movie: Movie)
}
