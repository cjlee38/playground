package com.example.webfluxplayground.reactor.schedulers

import reactor.core.scheduler.Schedulers

class TheSchedulers {
    fun test() {
        Schedulers.immediate()
        Schedulers.single()
        Schedulers.parallel()
        Schedulers.elastic()
        Schedulers.boundedElastic()
    }
}
