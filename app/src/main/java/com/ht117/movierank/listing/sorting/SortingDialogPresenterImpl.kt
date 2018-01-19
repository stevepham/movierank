package com.ht117.movierank.listing.sorting

/**
 * @author Quang Pham
 */
class SortingDialogPresenterImpl(private val sortingDialogInteractor: SortingDialogInteractor) : SortingDialogPresenter {
    private var view: SortingDialogView? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun setView(view: SortingDialogView) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun setLastSavedOption() {
        if (isViewAttached) {
            val selectedOption = sortingDialogInteractor.selectedSortingOption

            when (selectedOption) {
                SortType.MOST_POPULAR.value -> view!!.setPopularChecked()
                SortType.HIGHEST_RATED.value -> view!!.setHighestRatedChecked()
                else -> view!!.setFavoritesChecked()
            }
        }
    }

    override fun onPopularMoviesSelected() {
        if (isViewAttached) {
            sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR)
            view!!.dismissDialog()
        }
    }

    override fun onHighestRatedMoviesSelected() {
        if (isViewAttached) {
            sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED)
            view!!.dismissDialog()
        }
    }

    override fun onFavoritesSelected() {
        if (isViewAttached) {
            sortingDialogInteractor.setSortingOption(SortType.FAVORITES)
            view!!.dismissDialog()
        }
    }
}
