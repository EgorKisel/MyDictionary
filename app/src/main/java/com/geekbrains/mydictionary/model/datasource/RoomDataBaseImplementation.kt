package com.geekbrains.mydictionary.model.datasource

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.dto.SearchResultDto
import com.geekbrains.mydictionary.room.DataSourceLocal
import com.geekbrains.mydictionary.room.HistoryDao
import com.geekbrains.mydictionary.utils.convertDataModelSuccessToEntity
import com.geekbrains.mydictionary.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}