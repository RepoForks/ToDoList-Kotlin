package com.kevicsalazar.todolist.presentation

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kevicsalazar.appkit_alerts.ext.Alert
import com.kevicsalazar.todolist.R
import com.kevicsalazar.todolist.model.Item
import com.kevicsalazar.todolist.utils.hideKeyboard
import com.kevicsalazar.todolist.utils.swipeToDismiss
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    var presenter: MainPresenter? = null
    var adapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, PreferenceManager.getDefaultSharedPreferences(this))
        adapter = ListAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.swipeToDismiss {
            adapter?.remove(it)?.let {
                presenter?.deleteTask(it)
            }
        }

        presenter?.loadDataSaved()

        btnSend.setOnClickListener {
            presenter?.saveTask(etTask.text.toString(), spCategories.selectedItem as String)
            hideKeyboard(etTask)
            etTask.setText("")
        }

    }

    override fun clearAdapter() {
        adapter?.clear()
    }

    override fun addTaskToAdapter(item: Item) {
        adapter?.add(item)
    }

    override fun showMessage(title: String, message: String) {
        Alert(title, message) { confirmButton("OK") }.show()
    }

}
