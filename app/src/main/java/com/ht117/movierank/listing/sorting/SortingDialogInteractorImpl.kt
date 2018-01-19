package com.ht117.movierank.listing.sorting

/**
 * @author Quang Pham
 */
class SortingDialogInteractorImpl(val sortingOptionStore: SortingOptionStore) : SortingDialogInteractor {

    override val selectedSortingOption: Int
        get() = sortingOptionStore.selectedOption
    override fun setSortingOption(sortType: SortType) {
        sortingOptionStore.setSelectedOption(sortType)
    }
}
