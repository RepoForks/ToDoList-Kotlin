package com.kevicsalazar.todolist.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Created by Kevin.
 */

val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager