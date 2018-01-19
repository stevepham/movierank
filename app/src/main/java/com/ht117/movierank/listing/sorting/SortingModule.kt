package com.ht117.movierank.listing.sorting

import dagger.Module
import dagger.Provides

/**
 * @author Quang Pham
 */
@Module
class SortingModule {
    @Provides
    fun providesSortingDialogInteractor(store: SortingOptionStore): SortingDialogInteractor {
        return SortingDialogInteractorImpl(store)
    }

    @Provides
    fun providePresenter(interactor: SortingDialogInteractor): SortingDialogPresenter {
        return SortingDialogPresenterImpl(interactor)
    }
}
