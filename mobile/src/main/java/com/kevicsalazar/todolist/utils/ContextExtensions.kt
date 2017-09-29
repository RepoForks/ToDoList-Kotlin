package com.kevicsalazar.todolist.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Kevin.
 */

val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.hideKeyboard(view: View) {
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.alert(title: String, message: String, init: (AlertDialog.Builder.() -> Unit)? = null) = AlertDialog.Builder(this).apply {
    setTitle(title)
    setMessage(message)
    setPositiveButton("OK", { dialog, w -> dialog.dismiss() })
    init?.let { init() }
}