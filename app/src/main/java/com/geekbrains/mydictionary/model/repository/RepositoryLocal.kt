package com.geekbrains.mydictionary.model.repository

import com.geekbrains.mydictionary.model.data.AppState

interface RepositoryLocal<T>: Repository<T> {

    suspend fun saveToDB(appState: AppState)
}