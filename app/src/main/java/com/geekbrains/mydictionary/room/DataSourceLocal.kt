package com.geekbrains.mydictionary.room

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.datasource.DataSource

interface DataSourceLocal<T>: DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}