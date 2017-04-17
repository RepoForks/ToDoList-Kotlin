package com.kevicsalazar.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import com.kevicsalazar.todolist.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    var presenter: MainPresenter? = null
    var adapter: ListAdapter? = null
    var categories: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, PreferenceManager.getDefaultSharedPreferences(this))

        categories = resources.getStringArray(R.array.categories).toList()

        adapter = ListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenter?.loadDataSaved()

        btnSend.setOnClickListener {
            presenter?.saveTask(etTask.text.toString(), categories?.get(0) ?: "")
        }

    }

    override fun clearAdapter() {
        adapter?.clear()
    }

    override fun addTaskToAdapter(item: Item) {
        adapter?.add(item)
    }

}
