package com.geekbrains.mydictionary.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.presenter.Presenter
import com.geekbrains.mydictionary.viewmodel.BaseViewModel

abstract class BaseActivity <T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

//    protected lateinit var presenter: Presenter<T, View>
//
//    protected abstract fun createPresenter(): Presenter<T, View>

    //abstract override fun renderData(appState: AppState)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        presenter = createPresenter()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        presenter.attachView(this)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        presenter.detachView(this)
//    }
}