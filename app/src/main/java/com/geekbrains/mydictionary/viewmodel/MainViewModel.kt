package com.geekbrains.mydictionary.viewmodel

import androidx.lifecycle.LiveData
import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.datasource.DataSourceLocal
import com.geekbrains.mydictionary.model.datasource.DataSourceRemote
import com.geekbrains.mydictionary.model.repository.RepositoryImplementation
import com.geekbrains.mydictionary.view.main.MainInteractor
import io.reactivex.observers.DisposableObserver

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnLine: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnLine)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe{ liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnLine)
    }

    private fun getObserver() : DisposableObserver<AppState>{
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
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