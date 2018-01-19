package com.ht117.movierank.listing


import com.ht117.movierank.RxSchedulerRule
import com.ht117.movierank.model.Movie

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import io.reactivex.Observable

import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesListingPresenterImplTest {
    @Rule
    var rule = RxSchedulerRule()
    @Mock
    private val interactor: MoviesListingInteractor? = null
    @Mock
    private val view: MoviesListingView? = null
    @Mock
    private val movies: List<Movie>? = null

    private var presenter: MoviesListingPresenterImpl? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = MoviesListingPresenterImpl(interactor!!)
    }

    @After
    fun teardown() {
        presenter!!.destroy()
    }

    @Test
    fun shouldBeAbleToDisplayMovies() {
        // given:
        val responseObservable = Observable.just(movies!!)
        `when`(interactor!!.fetchMovies()).thenReturn(responseObservable)

        // when:
        presenter!!.setView(view!!)

        // then:
        verify(view).showMovies(movies)
    }
}