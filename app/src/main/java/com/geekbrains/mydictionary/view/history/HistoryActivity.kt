package com.geekbrains.mydictionary.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.mydictionary.R
import com.geekbrains.mydictionary.databinding.ActivityHistoryBinding
import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.utils.viewById
import com.geekbrains.mydictionary.view.base.BaseActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    private val historyActivityRecyclerview by viewById<RecyclerView>(R.id.history_activity_recyclerview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        //val viewModel: HistoryViewModel by currentScope.inject() // так не работает, не знаю почему
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> {
            renderData(it) })
    }

    private fun initViews() {
        historyActivityRecyclerview.adapter = adapter
    }
}