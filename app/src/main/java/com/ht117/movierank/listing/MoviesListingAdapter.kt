package com.ht117.movierank.listing

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.ht117.movierank.Api
import com.ht117.movierank.model.Movie
import com.ht117.movierank.R

/**
 * @author Quang Pham
 */
class MoviesListingAdapter(private val movies: List<Movie>, val view: MoviesListingView) : RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>() {
    private var context: Context? = null

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {
        @BindView(R.id.movie_poster)
        lateinit var poster: ImageView
        @BindView(R.id.title_background)
        lateinit var titleBackground: View
        @BindView(R.id.movie_name)
        lateinit var name: TextView

        var movie: Movie? = null

        init {
            ButterKnife.bind(this, root)
        }

        override fun onClick(view: View) {
            this@MoviesListingAdapter.view.onMovieClicked(movie!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false)

        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(holder)
        holder.movie = movies[position]
        holder.name!!.setText(holder.movie!!.title)

        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(context!!)
                .asBitmap()
                .load(Api.getPosterPath(holder.movie!!.posterPath!!))
                .apply(options)
                .into(object : BitmapImageViewTarget(holder.poster!!) {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate { palette -> setBackgroundColor(palette, holder) }
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder) {
        holder.titleBackground!!.setBackgroundColor(palette.getVibrantColor(context!!
                .resources.getColor(R.color.black_translucent_60)))
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
