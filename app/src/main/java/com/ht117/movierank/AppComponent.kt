package com.ht117.movierank


import com.ht117.movierank.favorites.FavoritesModule
import com.ht117.movierank.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Quang Pham
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, FavoritesModule::class))
interface AppComponent {
    operator fun plus(detailsModule: com.ht117.movierank.details.DetailsModule): com.ht117.movierank.details.DetailsComponent

    operator fun plus(listingModule: com.ht117.movierank.listing.ListingModule): com.ht117.movierank.listing.ListingComponent
}
