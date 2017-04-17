package com.kevicsalazar.todolist

import android.content.SharedPreferences
import com.kevicsalazar.todolist.model.Item
import com.kevicsalazar.todolist.utils.any
import com.kevicsalazar.todolist.utils.put
import java.util.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class MainPresenter(val view: View, val pref: SharedPreferences) {

    fun loadDataSaved() {
        val items = pref.any<List<Item>>("items")
        items?.forEach { view.addTaskToAdapter(it) }
    }

    fun saveTask(task: String, category: String) {
        val items = pref.any<MutableList<Item>>("items") ?: mutableListOf()
        val item = Item(UUID.randomUUID().toString(), task, category)
        items.add(item)
        pref.put("items", items)
        view.addTaskToAdapter(item)
    }

    fun deleteTask(item: Item) {
        val items = pref.any<MutableList<Item>>("items")
        items?.let {
            it.removeIf { it.id == item.id }
            pref.put("items", it)
        }
    }

    interface View {

        fun clearAdapter()

        fun addTaskToAdapter(item: Item)

    }

}