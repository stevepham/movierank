package com.ht117.movierank

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * @author arun
 */
class Video : Parcelable {

    var id: String? = null
    var name: String? = null
    var site: String? = null
    @SerializedName("key")
    var videoId: String? = null
    var size: Int = 0
    var type: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        name = `in`.readString()
        site = `in`.readString()
        videoId = `in`.readString()
        size = `in`.readInt()
        type = `in`.readString()
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

    companion object {
        val SITE_YOUTUBE = "YouTube"

        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(`in`: Parcel): Video {
                return Video(`in`)
            }

            override fun newArray(size: Int): Array<Video?> {
                return arrayOfNulls(size)
            }
        }

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
