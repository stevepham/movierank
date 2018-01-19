package com.ht117.movierank.listing

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.DrawerLayout.DrawerListener
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.ht117.movierank.Constants
import com.ht117.movierank.R
import com.ht117.movierank.details.MovieDetailsActivity
import com.ht117.movierank.details.MovieDetailsFragment
import com.ht117.movierank.model.Movie


class MoviesListingActivity : AppCompatActivity(), MoviesListingFragment.Callback {
    private var twoPaneMode: Boolean = false
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerView: NavigationView
    private lateinit var contentLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentLayout = findViewById(R.id.content)

        setupDrawer()

        setToolbar()

        if (findViewById<View>(R.id.movie_details_container) != null) {
            twoPaneMode = true

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.movie_details_container, MovieDetailsFragment())
                        .commit()
            }
        } else {
            twoPaneMode = false
        }
    }

    private fun setupDrawer() {
        drawerView = findViewById(R.id.drawer)
        drawerView.setNavigationItemSelectedListener { item:MenuItem ->
            drawerLayout.closeDrawers()
            when (item.itemId) {
                R.id.home -> {
                    changeView(R.id.content, MoviesListingFragment())
                }
                else -> false
            }
            false
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.addDrawerListener(drawerListener)
    }

    private fun changeView(id: Int, content: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id, content)
        transaction.commit()
        drawerLayout.closeDrawers()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.movie_guide)
            supportActionBar!!.setDisplayShowTitleEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onMoviesLoaded(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        }
    }

    override fun onMovieClicked(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        } else {
            startMovieActivity(movie)
        }
    }

    private fun startMovieActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        val extras = Bundle()
        extras.putParcelable(Constants.MOVIE, movie)
        intent.putExtras(extras)
        startActivity(intent)
    }

    private fun loadMovieFragment(movie: Movie) {
        val movieDetailsFragment = MovieDetailsFragment.Companion.getInstance(movie)
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit()
    }

    object drawerListener : DrawerListener {
        override fun onDrawerStateChanged(newState: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onDrawerClosed(drawerView: View) {
            MoviesListingActivity::invalidateOptionsMenu
        }
        override fun onDrawerOpened(drawerView: View) {
            MoviesListingActivity::invalidateOptionsMenu
        }
    }

    companion object {
        val DETAILS_FRAGMENT = "DetailsFragment"
    }
}
