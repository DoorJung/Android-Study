package com.icoo.androidstudy.util

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun mainThread(): Scheduler
}