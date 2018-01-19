package com.ht117.movierank.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import com.ht117.movierank.Api
import com.ht117.movierank.Constants

/**
 * @author Quang Pham
 */
class Video : Parcelable {

    var id: String? = null
    var name: String? = null
    var site: String? = null
    @SerializedName("key")
    var videoId: String? = null
    var size: Int = 0
    var type: String? = null

    private constructor(input: Parcel) {
        id = input.readString()
        name = input.readString()
        site = input.readString()
        videoId = input.readString()
        size = input.readInt()
        type = input.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeString(videoId)
        parcel.writeInt(size)
        parcel.writeString(type)
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }

        val SITE_YOUTUBE = "YouTube"

        fun getUrl(video: Video): String {
            return if (SITE_YOUTUBE.equals(video.site!!, ignoreCase = true)) {
                String.format(Api.YOUTUBE_VIDEO_URL, video.videoId)
            } else {
                Constants.EMPTY
            }
        }

        fun getThumbnailUrl(video: Video): String {
            return if (SITE_YOUTUBE.equals(video.site!!, ignoreCase = true)) {
                String.format(Api.YOUTUBE_THUMBNAIL_URL, video.videoId)
            } else {
                Constants.EMPTY
            }
        }
    }
}

class VideoWrapper {

    @SerializedName("results")
    var videos: List<Video>? = null

}
