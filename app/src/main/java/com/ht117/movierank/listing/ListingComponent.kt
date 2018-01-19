package com.ht117.movierank.listing

import com.ht117.movierank.listing.sorting.SortingDialogFragment
import com.ht117.movierank.listing.sorting.SortingModule
import dagger.Subcomponent

/**
 * @author Quang Pham
 */
@ListingScope
@Subcomponent(modules = [ListingModule::class, SortingModule::class])
interface ListingComponent {
    fun inject(fragment: MoviesListingFragment): MoviesListingFragment

    fun inject(fragment: SortingDialogFragment): SortingDialogFragment
}
