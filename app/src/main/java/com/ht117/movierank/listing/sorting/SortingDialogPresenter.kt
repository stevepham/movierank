package com.ht117.movierank.listing.sorting

/**
 * @author Quang Pham
 */
interface SortingDialogPresenter {
    fun setLastSavedOption()

    fun onPopularMoviesSelected()

    fun onHighestRatedMoviesSelected()

    fun onFavoritesSelected()

    fun setView(view: SortingDialogView)

    fun destroy()
}
