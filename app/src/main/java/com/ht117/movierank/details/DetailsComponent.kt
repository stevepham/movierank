package com.ht117.movierank.details

import dagger.Subcomponent

/**
 * @author Quang Pham
 */
@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(target: MovieDetailsFragment)
}
