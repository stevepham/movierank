package com.ht117.movierank.listing.sorting

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

/**
 * @author Quang Pham
 */
class SortingOptionStore @Inject
constructor(context: Context) {
    private val pref: SharedPreferences

    val selectedOption: Int
        get() = pref.getInt(SELECTED_OPTION, 0)

    init {
        pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setSelectedOption(sortType: SortType) {
        val editor = pref.edit()
        editor.putInt(SELECTED_OPTION, sortType.value)
        editor.apply()
    }

    companion object {
        private val SELECTED_OPTION = "selectedOption"
        private val PREF_NAME = "SortingOptionStore"
    }
}
