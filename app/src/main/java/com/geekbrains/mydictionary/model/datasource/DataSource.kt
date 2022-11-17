package com.geekbrains.mydictionary.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}