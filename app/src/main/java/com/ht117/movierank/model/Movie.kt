package com.ht117.movierank.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class Movie : Parcelable {
    var id: String? = null
    var overview: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    var title: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    private constructor(input: Parcel) {
        id = input.readString()
        overview = input.readString()
        releaseDate = input.readString()
        posterPath = input.readString()
        backdropPath = input.readString()
        title = input.readString()
        voteAverage = input.readDouble()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(title)
        parcel.writeDouble(voteAverage)
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

class MoviesWraper {

    @SerializedName("results")
    var movieList: List<Movie>? = null
}
