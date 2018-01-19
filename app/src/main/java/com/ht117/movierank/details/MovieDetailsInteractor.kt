package com.ht117.movierank.details

import com.ht117.movierank.model.Review
import com.ht117.movierank.model.Video
import io.reactivex.Observable

/**
 * @author Quang Pham
 */
interface MovieDetailsInteractor {
    fun getTrailers(id: String): Observable<List<Video>>
    fun getReviews(id: String): Observable<List<Review>>
}
