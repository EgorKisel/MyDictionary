package com.geekbrains.mydictionary.utils

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.model.dto.SearchResultDto
import com.geekbrains.mydictionary.model.userdata.DataModel
import com.geekbrains.mydictionary.model.userdata.Meaning
import com.geekbrains.mydictionary.model.userdata.TranslatedMeaning
import com.geekbrains.mydictionary.room.HistoryEntity

fun mapSearchResultToResult(searchResults: List<SearchResultDto>): List<DataModel> {
    return searchResults.map { searchResult ->
        var meanings: List<Meaning> = listOf()
        searchResult.meanings?.let {
            meanings = it.map { meaningsDto ->
                Meaning(
                    TranslatedMeaning(meaningsDto?.translation?.translation ?: ""),
                    meaningsDto?.imageUrl ?: ""
                )
            }
        }
        DataModel(searchResult.text ?: "", meanings)
    }
}

fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    appState: AppState,
    isOnline: Boolean
): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {}
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newSearchDataModels: ArrayList<DataModel>
) {
    val searchDataModels: List<DataModel> = appState.data as List<DataModel>
    if (searchDataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in searchDataModels) {
                parseOnlineResult(searchResult, newSearchDataModels)
            }
        } else {
            for (searchResult in searchDataModels) {
                newSearchDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(
    searchDataModel: DataModel,
    newSearchDataModels: ArrayList<DataModel>
) {
    if (searchDataModel.text.isNotBlank() && searchDataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        newMeanings.addAll(searchDataModel.meanings.filter { it.translatedMeaning.translatedMeaning.isNotBlank() })
        if (newMeanings.isNotEmpty()) {
            newSearchDataModels.add(
                DataModel(
                    searchDataModel.text,
                    newMeanings
                )
            )
        }
    }
}

//fun parseSearchResults(state: AppState): AppState {
//    val newSearchResults = arrayListOf<SearchResultDto>()
//    when (state) {
//        is AppState.Success -> {
//            val searchResults = state.data
//            if (!searchResults.isNullOrEmpty()) {
//                for (searchResult in searchResults) {
//                    parseResult(searchResult, newSearchResults)
//                }
//            }
//        }
//        is AppState.Error -> TODO()
//        is AppState.Loading -> TODO()
//    }
//
//    return AppState.Success(newSearchResults)
//}

//private fun parseResult(
//    searchResultDto: SearchResultDto,
//    newSearchResultDtos: ArrayList<SearchResultDto>
//) {
//    if (!searchResultDto.text.isNullOrBlank() && !searchResultDto.meanings.isNullOrEmpty()) {
//        val newMeanings = arrayListOf<MeaningsDto>()
//        for (meaning in searchResultDto.meanings) {
//            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
//                newMeanings.add(
//                    MeaningsDto(
//                        meaning.translation,
//                        meaning.imageUrl,
//                        meaning.soundUrl,
//                        meaning.previewUrl
//                    )
//                )
//            }
//        }
//        if (newMeanings.isNotEmpty()) {
//            newSearchResultDtos.add(SearchResultDto(searchResultDto.text, newMeanings))
//        }
//    }
//}

fun convertMeaningsToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translatedMeaning.translatedMeaning, ", ")
        } else {
            meaning.translatedMeaning.translatedMeaning
        }
    }
    return meaningsSeparatedByComma
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDto> {
    val searchResultDto = ArrayList<SearchResultDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResultDto.add(SearchResultDto(entity.word, null))
            searchResultDto.add(SearchResultDto(entity.translation, null))
            searchResultDto.add(SearchResultDto(entity.description, null))
        }
    }
    return searchResultDto
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null, null)
            }
        }
        else -> null
    }
}

fun convertMeaningsToSingleString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translatedMeaning.translatedMeaning, ", ")
        } else {
            meaning.translatedMeaning.translatedMeaning
        }
    }
    return meaningsSeparatedByComma
}