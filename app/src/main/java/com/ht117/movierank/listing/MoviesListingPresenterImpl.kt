package com.ht117.movierank.listing


import com.ht117.movierank.model.Movie
import com.ht117.movierank.util.RxUtils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Quang Pham
 */
internal class MoviesListingPresenterImpl(val moviesInteractor: MoviesListingInteractor) : MoviesListingPresenter {
    private var view: MoviesListingView? = null
    private var fetchSubscription: Disposable? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun setView(view: MoviesListingView) {
        this.view = view
        displayMovies()
    }

    override fun destroy() {
        view = null
        RxUtils.unsubscribe(fetchSubscription)
    }

    override fun displayMovies() {
        showLoading()
        fetchSubscription = moviesInteractor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onMovieFetchSuccess(it) }, { this.onMovieFetchFailed(it) })
    }

    private fun showLoading() {
        if (isViewAttached) {
            view!!.loadingStarted()
        }
    }

    private fun onMovieFetchSuccess(movies: List<Movie>) {
        if (isViewAttached) {
            view!!.showMovies(movies)
        }
    }

    private fun onMovieFetchFailed(e: Throwable) {
        view!!.loadingFailed(e.message!!)
    }
}
