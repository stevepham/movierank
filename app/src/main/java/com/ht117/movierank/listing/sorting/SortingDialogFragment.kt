package com.ht117.movierank.listing.sorting


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.ht117.movierank.BaseApplication
import com.ht117.movierank.R
import com.ht117.movierank.listing.MoviesListingPresenter
import javax.inject.Inject

/**
 * @author Quang Pham
 */
class SortingDialogFragment : DialogFragment(), SortingDialogView, RadioGroup.OnCheckedChangeListener {
    @Inject
    lateinit var sortingDialogPresenter: SortingDialogPresenter

    @BindView(R.id.most_popular)
    lateinit var mostPopular: RadioButton
    @BindView(R.id.highest_rated)
    lateinit var highestRated: RadioButton
    @BindView(R.id.favorites)
    lateinit var favorites: RadioButton
    @BindView(R.id.sorting_group)
    lateinit var sortingOptionsGroup: RadioGroup

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity!!.application as BaseApplication).listingComponent!!.inject(this)
        sortingDialogPresenter!!.setView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.sorting_options, null)
        unbinder = ButterKnife.bind(this, dialogView)
        initViews()

        val dialog = Dialog(activity!!)
        dialog.setContentView(dialogView)
        dialog.setTitle(R.string.sort_by)
        dialog.show()
        return dialog
    }

    private fun initViews() {
        sortingDialogPresenter!!.setLastSavedOption()
        sortingOptionsGroup!!.setOnCheckedChangeListener(this)
    }

    override fun setPopularChecked() {
        mostPopular!!.isChecked = true
    }

    override fun setHighestRatedChecked() {
        highestRated!!.isChecked = true
    }

    override fun setFavoritesChecked() {
        favorites.isChecked = true
    }

    override fun onCheckedChanged(radioGroup: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.most_popular -> {
                sortingDialogPresenter!!.onPopularMoviesSelected()
                moviesListingPresenter!!.displayMovies()
            }

            R.id.highest_rated -> {
                sortingDialogPresenter!!.onHighestRatedMoviesSelected()
                moviesListingPresenter!!.displayMovies()
            }

            R.id.favorites -> {
                sortingDialogPresenter!!.onFavoritesSelected()
                moviesListingPresenter!!.displayMovies()
            }
        }
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sortingDialogPresenter!!.destroy()
        unbinder!!.unbind()
    }

    companion object {

        var moviesListingPresenter: MoviesListingPresenter? = null

        fun newInstance(moviesListingPresenter: MoviesListingPresenter): SortingDialogFragment {
            SortingDialogFragment.moviesListingPresenter = moviesListingPresenter
            return SortingDialogFragment()
        }
    }
}
