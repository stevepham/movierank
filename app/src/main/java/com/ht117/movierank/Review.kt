package com.ht117.movierank

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Quang Pham
 */
class Review : Parcelable {
    var id: String? = null
    var author: String? = null
    var content: String? = null
    var url: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        author = `in`.readString()
        content = `in`.readString()
        url = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(url)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Review> = object : Parcelable.Creator<Review> {
            override fun createFromParcel(`in`: Parcel): Review {
                return Review(`in`)
            }

            override fun newArray(size: Int): Array<Review?> {
                return arrayOfNulls(size)
            }
        }
    }
}
