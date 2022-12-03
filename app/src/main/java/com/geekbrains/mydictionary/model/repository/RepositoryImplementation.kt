package com.geekbrains.mydictionary.model.repository

import com.geekbrains.mydictionary.model.datasource.DataSource
import com.geekbrains.mydictionary.model.dto.SearchResultDto

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}