package com.geekbrains.mydictionary.model.repository

import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}