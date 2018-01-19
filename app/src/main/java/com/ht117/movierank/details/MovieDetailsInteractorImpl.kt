package com.ht117.movierank.details


import com.ht117.movierank.Review
import com.ht117.movierank.Video
import com.ht117.movierank.network.TmdbWebService
import io.reactivex.Observable

/**
 * @author Quang Pham
 */
class MovieDetailsInteractorImpl(val tmdbWebService: TmdbWebService) : MovieDetailsInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return tmdbWebService.trailers(id).map { it.videos }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return tmdbWebService.reviews(id).map( { it.reviews })
    }

}
