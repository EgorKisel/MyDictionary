package com.geekbrains.mydictionary.model.repository

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.dto.SearchResultDto
import com.geekbrains.mydictionary.room.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>):
    RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}