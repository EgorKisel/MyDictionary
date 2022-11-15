package com.geekbrains.mydictionary.di

import com.geekbrains.mydictionary.model.data.DataModel
import com.geekbrains.mydictionary.model.repository.Repository
import com.geekbrains.mydictionary.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}