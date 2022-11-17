package com.geekbrains.mydictionary.model.repository

import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}