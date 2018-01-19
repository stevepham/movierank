package com.ht117.movierank.listing.sorting

/**
 * @author Quang Pham
 */
interface SortingDialogInteractor {
    val selectedSortingOption: Int

    fun setSortingOption(sortType: SortType)
}
