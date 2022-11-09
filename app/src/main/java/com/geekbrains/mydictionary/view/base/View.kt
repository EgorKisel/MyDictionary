package com.geekbrains.mydictionary.view.base

import com.geekbrains.mydictionary.model.data.AppState

interface View {

    fun renderData(appState: AppState)
}