package com.geekbrains.mydictionary.di

import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.datasource.RetrofitImplementation
import com.geekbrains.mydictionary.model.datasource.RoomDataBaseImplementation
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.model.repository.RepositoryImplementation
import com.geekbrains.mydictionary.view.main.MainInteractor
import com.geekbrains.mydictionary.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}