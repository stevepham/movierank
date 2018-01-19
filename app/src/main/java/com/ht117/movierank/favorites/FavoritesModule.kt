package com.ht117.movierank.favorites


import com.ht117.movierank.AppModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Quang Pham
 */
@Module(includes = [AppModule::class])
class FavoritesModule {
    @Provides
    @Singleton
    internal fun provideFavouritesInteractor(store: FavoritesStore): FavoritesInteractor {
        return FavoritesInteractorImpl(store)
    }
}
