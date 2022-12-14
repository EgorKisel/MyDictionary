package com.geekbrains.mydictionary.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}