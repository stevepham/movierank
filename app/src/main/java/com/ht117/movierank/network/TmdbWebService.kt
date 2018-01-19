package com.ht117.movierank.network

import com.ht117.movierank.MoviesWraper
import com.ht117.movierank.ReviewsWrapper
import com.ht117.movierank.VideoWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ivan on 8/20/2017.
 */

interface TmdbWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(): Observable<MoviesWraper>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun highestRatedMovies(): Observable<MoviesWraper>

    @GET("3/movie/{movieId}/videos")
    fun trailers(@Path("movieId") movieId: String): Observable<VideoWrapper>

    @GET("3/movie/{movieId}/reviews")
    fun reviews(@Path("movieId") movieId: String): Observable<ReviewsWrapper>

}
