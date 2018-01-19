package com.ht117.movierank.details


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ht117.movierank.*
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieDetailsFragment : Fragment(), MovieDetailsView, View.OnClickListener {
    @Inject
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    @BindView(R.id.movie_poster)
    lateinit var poster: ImageView
    @BindView(R.id.collapsing_toolbar)
    lateinit var collapsingToolbar: CollapsingToolbarLayout
    @BindView(R.id.movie_name)
    lateinit var title: TextView
    @BindView(R.id.movie_year)
    lateinit var releaseDate: TextView
    @BindView(R.id.movie_rating)
    lateinit var rating: TextView
    @BindView(R.id.movie_description)
    lateinit var overview: TextView
    @BindView(R.id.trailers_label)
    lateinit var label: TextView
    @BindView(R.id.trailers)
    lateinit var trailers: LinearLayout
    @BindView(R.id.trailers_container)
    lateinit var horizontalScrollView: HorizontalScrollView
    @BindView(R.id.reviews_label)
    lateinit var reviews: TextView
    @BindView(R.id.reviews)
    lateinit var reviewsContainer: LinearLayout
    @BindView(R.id.favorite)
    lateinit var favorite: FloatingActionButton
    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private var movie: Movie? = null
    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity!!.application as BaseApplication).createDetailsComponent()!!.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        unbinder = ButterKnife.bind(this, rootView)
        setToolbar()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val movie = arguments!!.get(Constants.MOVIE) as Movie
            if (movie != null) {
                this.movie = movie
                movieDetailsPresenter!!.setView(this)
                movieDetailsPresenter!!.showDetails(movie)
                movieDetailsPresenter!!.showFavoriteButton(movie)
            }
        }
    }

    private fun setToolbar() {
        collapsingToolbar!!.setContentScrimColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        collapsingToolbar!!.title = getString(R.string.movie_details)
        collapsingToolbar!!.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
        collapsingToolbar!!.setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
        collapsingToolbar!!.isTitleEnabled = true

        if (toolbar != null) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)

            val actionBar = (activity as AppCompatActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    override fun showDetails(movie: Movie) {
        Glide.with(context!!).load(Api.getBackdropPath(movie.backdropPath!!)).into(poster!!)
        title!!.setText(movie.title)
        releaseDate!!.text = String.format(getString(R.string.release_date), movie.releaseDate)
        rating!!.text = String.format(getString(R.string.rating), movie.voteAverage.toString())
        overview!!.setText(movie.overview)
        movieDetailsPresenter!!.showTrailers(movie)
        movieDetailsPresenter!!.showReviews(movie)
    }

    override fun showTrailers(trailers: List<Video>) {
        if (trailers.isEmpty()) {
            label!!.visibility = View.GONE
            this.trailers!!.visibility = View.GONE
            horizontalScrollView!!.visibility = View.GONE

        } else {
            label!!.visibility = View.VISIBLE
            this.trailers!!.visibility = View.VISIBLE
            horizontalScrollView!!.visibility = View.VISIBLE

            this.trailers!!.removeAllViews()
            val inflater = activity!!.layoutInflater
            val options = RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150)

            for (trailer in trailers) {
                val thumbContainer = inflater.inflate(R.layout.video, this.trailers, false)
                val thumbView = ButterKnife.findById(thumbContainer, R.id.video_thumb) as ImageView
                thumbView.setTag(Video.getUrl(trailer))
                thumbView.requestLayout()
                thumbView.setOnClickListener(this)

                Picasso.with(context!!)
                        .load(Video.getThumbnailUrl(trailer))
                        .into(thumbView)

                /*Glide.with(context!!)
                        .load(Video.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thumbView)*/
                this.trailers!!.addView(thumbContainer)
            }
        }
    }

    override fun showReviews(reviews: List<Review>) {
        if (reviews.isEmpty()) {
            this.reviews!!.visibility = View.GONE
            reviewsContainer!!.visibility = View.GONE
        } else {
            this.reviews!!.visibility = View.VISIBLE
            reviewsContainer!!.visibility = View.VISIBLE

            reviewsContainer!!.removeAllViews()
            val inflater = activity!!.layoutInflater
            for (review in reviews) {
                val reviewContainer = inflater.inflate(R.layout.review, reviewsContainer, false) as ViewGroup
                val reviewAuthor = ButterKnife.findById(reviewContainer, R.id.review_author) as TextView
                val reviewContent = ButterKnife.findById(reviewContainer, R.id.review_content) as TextView
                reviewAuthor.setText(review.author)
                reviewContent.setText(review.content)
                reviewContent.setOnClickListener(this)
                reviewsContainer!!.addView(reviewContainer)
            }
        }
    }

    override fun showFavorited() {
        favorite!!.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_white_24dp))
    }

    override fun showUnFavorited() {
        favorite!!.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_border_white_24dp))
    }

    @OnClick(R.id.favorite)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.video_thumb -> onThumbnailClick(view)

            R.id.review_content -> onReviewClick(view as TextView)

            R.id.favorite -> onFavoriteClick()

            else -> {
            }
        }
    }

    private fun onReviewClick(view: TextView) {
        if (view.maxLines == 5) {
            view.maxLines = 500
        } else {
            view.maxLines = 5
        }
    }

    private fun onThumbnailClick(view: View) {
        val videoUrl = view.tag as String
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(playVideoIntent)
    }

    private fun onFavoriteClick() {
        movieDetailsPresenter!!.onFavoriteClick(movie!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieDetailsPresenter!!.destroy()
        unbinder!!.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity!!.application as BaseApplication).releaseDetailsComponent()
    }

    companion object {

        fun getInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = args
            return movieDetailsFragment
        }
    }
}
