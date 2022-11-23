package com.geekbrains.mydictionary.di

import androidx.room.Room
import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.datasource.RetrofitImplementation
import com.geekbrains.mydictionary.model.datasource.RoomDataBaseImplementation
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.model.repository.RepositoryImplementation
import com.geekbrains.mydictionary.model.repository.RepositoryImplementationLocal
import com.geekbrains.mydictionary.model.repository.RepositoryLocal
import com.geekbrains.mydictionary.room.HistoryDataBase
import com.geekbrains.mydictionary.view.history.HistoryInteractor
import com.geekbrains.mydictionary.view.history.HistoryViewModel
import com.geekbrains.mydictionary.view.main.MainInteractor
import com.geekbrains.mydictionary.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build()}

    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}