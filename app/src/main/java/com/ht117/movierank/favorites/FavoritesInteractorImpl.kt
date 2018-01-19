package com.ht117.movierank.favorites

import com.ht117.movierank.model.Movie
import java.io.IOException

/**
 * @author Quang Pham
 */
class FavoritesInteractorImpl(val favoritesStore: com.ht117.movierank.favorites.FavoritesStore) : FavoritesInteractor {
    override val favorites: List<Movie>
        get() = try {
            favoritesStore.favorites
        } catch (exp: Exception) {
            emptyList()
        }

    override fun setFavorite(movie: Movie) {
        favoritesStore.setFavorite(movie)
    }

    override fun isFavorite(id: String): Boolean {
        return favoritesStore.isFavorite(id)
    }

    override fun unFavorite(id: String) {
        favoritesStore.unfavorite(id)
    }
}
