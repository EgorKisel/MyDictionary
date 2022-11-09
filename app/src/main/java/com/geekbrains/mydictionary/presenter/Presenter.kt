package com.geekbrains.mydictionary.presenter

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}