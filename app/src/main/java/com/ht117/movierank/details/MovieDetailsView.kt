package com.ht117.movierank.details

import com.ht117.movierank.Movie
import com.ht117.movierank.Review
import com.ht117.movierank.Video

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
