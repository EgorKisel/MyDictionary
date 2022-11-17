package com.geekbrains.mydictionary.presenter

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}