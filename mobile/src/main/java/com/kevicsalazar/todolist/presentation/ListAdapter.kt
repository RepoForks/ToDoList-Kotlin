package com.kevicsalazar.todolist.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevicsalazar.todolist.R
import com.kevicsalazar.todolist.model.Item
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val item = items[position]
            tvTitle.text = item.task
            tvCategory.text = item.category
        }
    }

    override fun getItemCount() = items.size

    fun add(item: Item) {
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun remove(position: Int): String {
        val item = items[position]
        items.remove(item)
        notifyItemRemoved(position)
        return item.id
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}