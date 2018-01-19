package com.ht117.movierank.details


import com.ht117.movierank.model.Review
import com.ht117.movierank.model.Video
import com.ht117.movierank.network.TmdbWebService
import io.reactivex.Observable

/**
 * @author Quang Pham
 */
class MovieDetailsInteractorImpl(private val service: TmdbWebService) : MovieDetailsInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return service.trailers(id).map { it.videos }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return service.reviews(id).map( { it.reviews })
    }

}
