package com.ht117.movierank


import com.ht117.movierank.details.DetailsComponent
import com.ht117.movierank.details.DetailsModule
import com.ht117.movierank.favorites.FavoritesModule
import com.ht117.movierank.listing.ListingComponent
import com.ht117.movierank.listing.ListingModule
import com.ht117.movierank.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Quang Pham
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, FavoritesModule::class])
interface AppComponent {
    operator fun plus(detailsModule: DetailsModule): DetailsComponent

    operator fun plus(listingModule: ListingModule): ListingComponent
}
