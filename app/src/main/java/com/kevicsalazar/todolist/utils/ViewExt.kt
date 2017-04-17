package com.kevicsalazar.todolist.utils

import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Kevin.
 */

fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}