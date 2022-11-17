package com.geekbrains.mydictionary.view.main

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.presenter.Interactor

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {

        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}