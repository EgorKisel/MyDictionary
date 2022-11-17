package com.geekbrains.mydictionary.model.datasource

import com.geekbrains.mydictionary.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}