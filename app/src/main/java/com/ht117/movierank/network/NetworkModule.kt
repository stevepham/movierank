package com.ht117.movierank.network


import com.ht117.movierank.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Quang Pham
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun requestInterceptor(interceptor: com.ht117.movierank.network.RequestInterceptor): Interceptor {
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(requestInterceptor: com.ht117.movierank.network.RequestInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun tmdbWebService(retrofit: Retrofit): TmdbWebService {
        return retrofit.create(TmdbWebService::class.java)
    }

    companion object {
        val CONNECT_TIMEOUT_IN_MS = 30000
    }

}
