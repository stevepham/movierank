package com.ht117.movierank.details

import com.ht117.movierank.model.Movie

/**
 * @author Quang Pham
 */
interface MovieDetailsPresenter {
    fun showDetails(movie: Movie)

    fun showTrailers(movie: Movie)

    fun showReviews(movie: Movie)

    fun showFavoriteButton(movie: Movie)

    fun onFavoriteClick(movie: Movie)

    fun setView(view: MovieDetailsView)

    fun destroy()
}
