package com.ht117.movierank

import com.google.gson.annotations.SerializedName

/**
 * Created by Quang Pham
 */

class MoviesWraper {

    @SerializedName("results")
    var movieList: List<Movie>? = null
}
