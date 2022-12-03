package com.geekbrains.mydictionary.view.history

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.dto.SearchResultDto
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.model.repository.RepositoryLocal
import com.geekbrains.mydictionary.presenter.Interactor
import com.geekbrains.mydictionary.utils.mapSearchResultToResult

class HistoryInteractor(
    private val remoteRepository: Repository<List<SearchResultDto>>,
    private val localRepository: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word))
        )
    }
}