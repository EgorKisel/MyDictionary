package com.geekbrains.mydictionary.model.userdata

data class DataModel (
    val text: String = "",
    val meanings: List<Meaning> = listOf()
)