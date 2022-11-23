package com.geekbrains.mydictionary.view.main

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.model.repository.RepositoryLocal
import com.geekbrains.mydictionary.presenter.Interactor

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
            val appState: AppState
            if (fromRemoteSource) {
                appState = AppState.Success(remoteRepository.getData(word))
                localRepository.saveToDB(appState)
            } else {
                appState = AppState.Success(localRepository.getData(word))
            }
            return appState
    }
}