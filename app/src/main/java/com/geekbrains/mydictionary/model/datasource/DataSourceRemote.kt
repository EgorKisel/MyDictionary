package com.geekbrains.mydictionary.model.datasource

import com.geekbrains.mydictionary.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}