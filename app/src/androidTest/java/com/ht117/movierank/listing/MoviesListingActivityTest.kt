package com.ht117.movierank.listing

import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.ht117.movierank.R

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.widget.RecyclerView
import com.ht117.movierank.R

/**
 * @author arunsasidharan
 */
@RunWith(AndroidJUnit4::class)
class MoviesListingActivityTest {
    @Rule
    val activityTestRule = ActivityTestRule<MoviesListingActivity>(MoviesListingActivity::class.java)

    @Test
    fun shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.action_sort)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun shouldBeAbleToLoadMovies() {
        Thread.sleep(3000)
        onView(withId(R.id.movies_listing)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun shouldBeAbleToScrollViewMovieDetails() {
        Thread.sleep(3000)
        onView(withId(R.id.movies_listing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withText("Summary")).check(matches(isDisplayed()))
    }
}
