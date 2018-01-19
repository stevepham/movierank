package com.ht117.movierank.listing.sorting


import com.ht117.movierank.RxSchedulerRule

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * @author Quang Pham
 */
@RunWith(MockitoJUnitRunner::class)
class SortingDialogPresenterImplTest {
    @Rule
    var rule = RxSchedulerRule()
    @Mock
    private val interactor: SortingDialogInteractor? = null
    @Mock
    private val view: SortingDialogView? = null

    private var presenter: SortingDialogPresenterImpl? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = SortingDialogPresenterImpl(interactor!!)
        presenter!!.setView(view!!)
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckPopularIfLastSavedOptionIsPopular() {
        `when`(interactor!!.selectedSortingOption).thenReturn(SortType.MOST_POPULAR.value)
        presenter!!.setLastSavedOption()
        verify<SortingDialogView>(view).setPopularChecked()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckHighestRatedIfLastSavedOptionIsHighestRated() {
        `when`(interactor!!.selectedSortingOption).thenReturn(SortType.HIGHEST_RATED.value)
        presenter!!.setLastSavedOption()
        verify<SortingDialogView>(view).setHighestRatedChecked()
    }

    @Test
    @Throws(Exception::class)
    fun shouldCheckFavoritesIfLastSavedOptionIsFavorites() {
        `when`(interactor!!.selectedSortingOption).thenReturn(SortType.FAVORITES.value)
        presenter!!.setLastSavedOption()
        verify<SortingDialogView>(view).setFavoritesChecked()
    }

    @Test
    @Throws(Exception::class)
    fun onPopularMoviesSelected() {
        presenter!!.onPopularMoviesSelected()
        verify<SortingDialogInteractor>(interactor).setSortingOption(SortType.MOST_POPULAR)
    }

    @Test
    @Throws(Exception::class)
    fun onHighestRatedMoviesSelected() {
        presenter!!.onHighestRatedMoviesSelected()
        verify<SortingDialogInteractor>(interactor).setSortingOption(SortType.HIGHEST_RATED)
    }

    @Test
    @Throws(Exception::class)
    fun onFavoritesSelected() {
        presenter!!.onFavoritesSelected()
        verify<SortingDialogInteractor>(interactor).setSortingOption(SortType.FAVORITES)
    }

}