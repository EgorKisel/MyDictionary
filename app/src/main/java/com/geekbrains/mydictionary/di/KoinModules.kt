package com.geekbrains.mydictionary.di

import androidx.room.Room
import com.geekbrains.mydictionary.model.datasource.RetrofitImplementation
import com.geekbrains.mydictionary.model.datasource.RoomDataBaseImplementation
import com.geekbrains.mydictionary.model.dto.SearchResultDto
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.model.repository.RepositoryImplementation
import com.geekbrains.mydictionary.model.repository.RepositoryImplementationLocal
import com.geekbrains.mydictionary.model.repository.RepositoryLocal
import com.geekbrains.mydictionary.room.HistoryDataBase
import com.geekbrains.mydictionary.view.history.HistoryInteractor
import com.geekbrains.mydictionary.view.history.HistoryViewModel
import com.geekbrains.mydictionary.view.main.MainInteractor
import com.geekbrains.mydictionary.viewmodel.MainViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()}

    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<SearchResultDto>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    //не работает
//    scope (named<MainActivity>()) {
//        viewModel {MainViewModel (get())}
//        scoped { MainInteractor (get(), get())}
//    }
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    //не работает
//    scope (named<HistoryActivity>()) {
//        viewModel {HistoryViewModel(get())}
//        scoped {HistoryInteractor(get(), get())}
//    }
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}