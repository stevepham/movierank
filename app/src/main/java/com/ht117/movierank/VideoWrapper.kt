package com.ht117.movierank

import com.google.gson.annotations.SerializedName

/**
 * Created by ivan on 8/20/2017.
 */

class VideoWrapper {

    @SerializedName("results")
    var videos: List<Video>? = null

}
