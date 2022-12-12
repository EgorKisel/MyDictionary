package com.geekbrains.mydictionary

import com.geekbrains.mydictionary.rx.ISchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderForTest : ISchedulerProvider {

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()
}