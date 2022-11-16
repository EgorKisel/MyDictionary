package com.geekbrains.mydictionary.koin

import com.geekbrains.mydictionary.di.application
import com.geekbrains.mydictionary.di.mainScreen
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.KoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

@Category(CheckModuleTest::class)
class CheckModulesTest : KoinTest {

    @Test
    fun checkAllModules() = checkModules {
        modules(application, mainScreen)
    }
}