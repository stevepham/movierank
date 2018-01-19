package com.ht117.movierank

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Quang Pham
 */
@Module
class AppModule constructor(application: Application) {
    private val context: Context

    init {
        context = application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}
