package com.ht117.movierank.details

import com.esoxjem.movierank.Movie
import com.esoxjem.movierank.Review
import com.esoxjem.movierank.RxSchedulerRule
import com.esoxjem.movierank.Video
import com.esoxjem.movierank.favorites.FavoritesInteractor
import com.ht117.movierank.RxSchedulerRule
import com.ht117.movierank.model.Movie
import com.ht117.movierank.model.Review
import com.ht117.movierank.model.Video
import com.ht117.movierank.favorites.FavoritesInteractor

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

import java.net.SocketTimeoutException

import io.reactivex.Observable

import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.Mockito.`when`

/**
 * @author Quang Pham
 */
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsPresenterImplTest {
    @Rule
    var rule = RxSchedulerRule()
    @Mock
    private val view: MovieDetailsView? = null
    @Mock
    private val movieDetailsInteractor: MovieDetailsInteractor? = null
    @Mock
    private val favoritesInteractor: FavoritesInteractor? = null
    @Mock
    var videos: List<Video>? = null
    @Mock
    var movie: Movie? = null
    @Mock
    var reviews: List<Review>? = null

    private var movieDetailsPresenter: MovieDetailsPresenterImpl? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieDetailsPresenter = MovieDetailsPresenterImpl(movieDetailsInteractor!!, favoritesInteractor!!)
        movieDetailsPresenter!!.setView(view!!)
    }

    @After
    fun teardown() {
        movieDetailsPresenter!!.destroy()
    }

    @Test
    fun shouldUnfavoriteIfFavoriteTapped() {
        `when`(movie!!.id).thenReturn("12345")
        `when`(favoritesInteractor!!.isFavorite(movie!!.id!!)).thenReturn(true)

        movieDetailsPresenter!!.onFavoriteClick(movie!!)

        verify<MovieDetailsView>(view).showUnFavorited()
    }

    @Test
    fun shouldFavoriteIfUnfavoriteTapped() {
        `when`(movie!!.id).thenReturn("12345")
        `when`(favoritesInteractor!!.isFavorite(movie!!.id!!)).thenReturn(false)

        movieDetailsPresenter!!.onFavoriteClick(movie!!)

        verify<MovieDetailsView>(view).showFavorited()
    }

    @Test
    fun shouldBeAbleToShowTrailers() {
        `when`(movie!!.id).thenReturn("12345")
        val responseObservable = Observable.just<List<Video>>(videos!!)
        `when`(movieDetailsInteractor!!.getTrailers(movie!!.id!!)).thenReturn(responseObservable)

        movieDetailsPresenter!!.showTrailers(movie!!)

        verify<MovieDetailsView>(view).showTrailers(videos!!)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFailSilentlyWhenNoTrailers() {
        `when`(movie!!.id).thenReturn("12345")
        `when`(movieDetailsInteractor!!.getTrailers(movie!!.id!!)).thenReturn(Observable.error { SocketTimeoutException() })

        movieDetailsPresenter!!.showTrailers(movie!!)

        verifyZeroInteractions(view)
    }

    @Test
    fun shouldBeAbleToShowReviews() {
        val responseObservable = Observable.just<List<Review>>(reviews!!)
        `when`(movie!!.id).thenReturn("12345")
        `when`(movieDetailsInteractor!!.getReviews(movie!!.id!!)).thenReturn(responseObservable)

        movieDetailsPresenter!!.showReviews(movie!!)

        verify<MovieDetailsView>(view).showReviews(reviews!!)
    }


    @Test
    @Throws(Exception::class)
    fun shouldFailSilentlyWhenNoReviews() {
        `when`(movie!!.id).thenReturn("12345")
        `when`(movieDetailsInteractor!!.getReviews(movie!!.id!!)).thenReturn(Observable.error{SocketTimeoutException()})

        movieDetailsPresenter!!.showReviews(movie!!)

        verifyZeroInteractions(view)
    }

}