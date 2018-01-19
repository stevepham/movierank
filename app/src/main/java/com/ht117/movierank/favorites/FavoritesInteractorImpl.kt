package com.ht117.movierank.favorites

import com.ht117.movierank.Movie
import java.io.IOException

/**
 * @author Quang Pham
 */
class FavoritesInteractorImpl(val favoritesStore: com.ht117.movierank.favorites.FavoritesStore) : FavoritesInteractor {

    override fun setFavorite(movie: Movie) {
        favoritesStore.setFavorite(movie)
    }

    override fun isFavorite(id: String): Boolean {
        return favoritesStore.isFavorite(id)
    }

    override fun getFavorites(): List<Movie> {
        try {
            return favoritesStore.favorites
        } catch (ignored: IOException) {
            return emptyList()
        }
    }

    override fun unFavorite(id: String) {
        favoritesStore.unfavorite(id)
    }
}
