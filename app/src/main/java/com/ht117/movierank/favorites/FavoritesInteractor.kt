package com.ht117.movierank.favorites


import com.ht117.movierank.model.Movie

/**
 * @author Quang Pham
 */
interface FavoritesInteractor {
    val favorites: List<Movie>
    fun setFavorite(movie: Movie)
    fun isFavorite(id: String): Boolean
    fun unFavorite(id: String)
}
