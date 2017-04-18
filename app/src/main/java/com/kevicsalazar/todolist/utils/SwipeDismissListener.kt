package com.kevicsalazar.todolist.utils

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * https://github.com/lightningkite/kotlin-anko
 */

open class SwipeDismissListener(val action: (Int) -> Unit) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(p0: RecyclerView?, holder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    override fun onMove(p0: RecyclerView?, p1: RecyclerView.ViewHolder?, p2: RecyclerView.ViewHolder?): Boolean = false

    override fun onSwiped(holder: RecyclerView.ViewHolder, swipeDirection: Int) {
        action(holder.adapterPosition)
    }
}

fun RecyclerView.swipeToDismiss(action: (Int) -> Unit) {
    val listener = SwipeDismissListener(action)
    ItemTouchHelper(listener).attachToRecyclerView(this)
}