package com.ht117.movierank

import com.google.gson.annotations.SerializedName

/**
 * Created by Quang Pham on 8/20/2017.
 */

class ReviewsWrapper {

    @SerializedName("results")
    var reviews: List<Review>? = null
}
