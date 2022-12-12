package com.geekbrains.mydictionary

import com.geekbrains.mydictionary.model.data.AppState
import com.geekbrains.mydictionary.rx.SchedulerProvider
import com.geekbrains.mydictionary.view.base.View
import com.geekbrains.mydictionary.view.main.MainInteractor
import com.geekbrains.mydictionary.view.main.MainPresenterImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations

class MainPresenterImplTest {

    private lateinit var presenter: MainPresenterImpl<AppState, View>

    private val mockInteractor = Mockito.mock(MainInteractor::class.java)

    private val mockView = Mockito.mock(View::class.java)

    private val mockDataModelSuccess = mock<AppState.Success>()

    private val mockDataModelError = mock<AppState.Error>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl<AppState, View>(mockInteractor, CompositeDisposable(), SchedulerProviderForTest())
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        presenter.detachView(mockView)
    }

    @Test
    fun `getData should return translation data`() {
        `when`(mockInteractor.getData( anyString(), anyBoolean() ) )
            .thenReturn(Observable.just(mockDataModelSuccess))

        presenter.getData(anyString(), anyBoolean())

        verify(mockView).renderData(mockDataModelSuccess)
        verify(mockView, never()).renderData(mockDataModelError)
    }

    @Test
    fun `getData should return Error data`()  {

        `when`(mockInteractor.getData( anyString(), anyBoolean() ) )
            .thenReturn(Observable.just(mockDataModelError))

        presenter.getData(anyString(), anyBoolean())

        Mockito.verify(mockView).renderData(mockDataModelError)
        Mockito.verify(mockView, never()).renderData(mockDataModelSuccess)
    }
}