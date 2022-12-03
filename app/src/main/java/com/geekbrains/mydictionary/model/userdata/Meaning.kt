package com.geekbrains.mydictionary.model.userdata

data class Meaning (
    val translatedMeaning: TranslatedMeaning = TranslatedMeaning(),
    val imageUrl: String = ""
)