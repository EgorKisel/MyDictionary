package com.geekbrains.mydictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T: AppState> (
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    abstract fun getData(word: String, isOnLine: Boolean)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}