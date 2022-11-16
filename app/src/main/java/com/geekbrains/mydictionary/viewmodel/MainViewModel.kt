package com.geekbrains.mydictionary.viewmodel

import androidx.lifecycle.LiveData
import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.view.main.MainInteractor
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class MainViewModel (
    private val interactor: MainInteractor
    ) : BaseViewModel<AppState>() {


    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnLine: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnLine)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading(null) }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}