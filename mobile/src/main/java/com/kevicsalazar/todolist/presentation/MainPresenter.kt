package com.kevicsalazar.todolist.presentation

import android.content.SharedPreferences
import com.kevicsalazar.todolist.model.Item
import com.kevicsalazar.todolist.utils.put
import java.util.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class MainPresenter(val view: View) {

    val items = mutableListOf<Item>()

    fun loadDataSaved() {
        items.forEach { view.addTaskToAdapter(it) }
    }

    fun saveTask(task: String, category: String) {

        if (task.isBlank()) {
            view.showMessage("Aviso", "Debe ingresar una tarea")
            return
        }

        val item = Item(UUID.randomUUID().toString(), task, category)
        items.add(item)
        view.addTaskToAdapter(item)

    }

    fun deleteTask(itemId: String) {
        items.removeIf({ it.id == itemId })
    }

    interface View {

        fun clearAdapter()

        fun addTaskToAdapter(item: Item)

        fun showMessage(title: String, message: String)

    }

}