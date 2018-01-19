package com.ht117.movierank.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * @author Quang Pham
 */
class Review : Parcelable {
    var id: String? = null
    var author: String? = null
    var content: String? = null
    var url: String? = null

    private constructor(input: Parcel) {
        id = input.readString()
        author = input.readString()
        content = input.readString()
        url = input.readString()
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

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}

class ReviewsWrapper {
    @SerializedName("results")
    var reviews: List<Review>? = null
}