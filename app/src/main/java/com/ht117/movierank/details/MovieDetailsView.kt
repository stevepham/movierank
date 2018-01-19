package com.ht117.movierank.details

import com.ht117.movierank.model.Movie
import com.ht117.movierank.model.Review
import com.ht117.movierank.model.Video

/**
 * @author Quang Pham
 */
interface MovieDetailsView {
    fun showDetails(movie: Movie)
    fun showTrailers(trailers: List<Video>)
    fun showReviews(reviews: List<Review>)
    fun showFavorited()
    fun showUnFavorited()
}
