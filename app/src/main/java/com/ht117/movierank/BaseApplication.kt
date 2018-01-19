package com.ht117.movierank

import android.app.Application
import android.os.StrictMode

import com.ht117.movierank.details.DetailsComponent
import com.ht117.movierank.details.DetailsModule
import com.ht117.movierank.favorites.FavoritesModule
import com.ht117.movierank.listing.ListingComponent
import com.ht117.movierank.listing.ListingModule
import com.ht117.movierank.network.NetworkModule
import dagger.internal.DaggerCollections

/**
 * @author Quang Pham
 */
class BaseApplication : Application() {
    private var appComponent: AppComponent? = null
    private var detailsComponent: DetailsComponent? = null
    var listingComponent: ListingComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .favoritesModule(FavoritesModule())
                .build()
    }

    fun createDetailsComponent(): DetailsComponent? {
        detailsComponent = appComponent!!.plus(DetailsModule())
        return detailsComponent
    }

    fun releaseDetailsComponent() {
        detailsComponent = null
    }

    fun createListingComponent(): ListingComponent? {
        listingComponent = appComponent!!.plus(ListingModule())
        return listingComponent
    }

    fun releaseListingComponent() {
        listingComponent = null
    }
}
