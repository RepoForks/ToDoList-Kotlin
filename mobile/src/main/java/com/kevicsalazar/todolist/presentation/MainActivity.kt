package com.kevicsalazar.todolist.presentation

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kevicsalazar.todolist.R
import com.kevicsalazar.todolist.model.Item
import com.kevicsalazar.todolist.utils.hideKeyboard
import com.kevicsalazar.todolist.utils.swipeToDismiss
import kotlinx.android.synthetic.main.activity_main.*
import com.kevicsalazar.todolist.utils.extensions.alert

class MainActivity : AppCompatActivity(), MainPresenter.View {

    var adapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        adapter = ListAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.swipeToDismiss {
            adapter?.remove(it)?.let {
                presenter?.deleteTask(it)
            }
        }

        btnSend.setOnClickListener {
            presenter?.saveTask(etTask.text.toString(), spCategories.selectedItem as String)
            hideKeyboard(etTask)
            etTask.setText("")
        }

        presenter?.loadDataSaved()
        
    }

    override fun clearAdapter() {
        adapter?.clear()
    }

    override fun addTaskToAdapter(item: Item) {
        adapter?.add(item)
    }

    override fun showMessage(title: String, message: String) {
        alert(title, message).show()
    }

}
