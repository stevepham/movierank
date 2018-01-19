package com.ht117.movierank.details

import com.ht117.movierank.Movie
import com.ht117.movierank.Review
import com.ht117.movierank.Video
import com.ht117.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.util.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Quang Pham
 */
class MovieDetailsPresenterImpl(val movieDetailsInteractor: MovieDetailsInteractor, val favoritesInteractor: FavoritesInteractor) : MovieDetailsPresenter {
    private var view: MovieDetailsView? = null
    private var trailersSubscription: Disposable? = null
    private var reviewSubscription: Disposable? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun setView(view: MovieDetailsView) {
        this.view = view
    }

    override fun destroy() {
        view = null
        RxUtils.unsubscribe(trailersSubscription!!, reviewSubscription!!)
    }

    override fun showDetails(movie: Movie) {
        if (isViewAttached) {
            view!!.showDetails(movie)
        }
    }

    override fun showTrailers(movie: Movie) {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onGetTrailersSuccess(it) })
    }

    private fun onGetTrailersSuccess(videos: List<Video>) {
        if (isViewAttached) {
            view!!.showTrailers(videos)
        }
    }

    private fun onGetTrailersFailure() {
        // Do nothing
    }

    override fun showReviews(movie: Movie) {
        reviewSubscription = movieDetailsInteractor.getReviews(movie.id!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onGetReviewsSuccess(it) })
    }

    private fun onGetReviewsSuccess(reviews: List<Review>) {
        if (isViewAttached) {
            view!!.showReviews(reviews)
        }
    }

    private fun onGetReviewsFailure() {
        // Do nothing
    }

    override fun showFavoriteButton(movie: Movie) {
        val isFavorite = favoritesInteractor.isFavorite(movie.id!!)
        if (isViewAttached) {
            if (isFavorite) {
                view!!.showFavorited()
            } else {
                view!!.showUnFavorited()
            }
        }
    }

    override fun onFavoriteClick(movie: Movie) {
        if (isViewAttached) {
            val isFavorite = favoritesInteractor.isFavorite(movie.id)
            if (isFavorite) {
                favoritesInteractor.unFavorite(movie.id)
                view!!.showUnFavorited()
            } else {
                favoritesInteractor.setFavorite(movie)
                view!!.showFavorited()
            }
        }
    }
}
