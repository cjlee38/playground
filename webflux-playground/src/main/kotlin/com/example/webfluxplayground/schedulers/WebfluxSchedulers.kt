package com.example.webfluxplayground.schedulers

import reactor.core.scheduler.Schedulers

class WebfluxSchedulers {
    fun test() {
        Schedulers.immediate()
        Schedulers.single()
        Schedulers.parallel()
        Schedulers.elastic()
        Schedulers.boundedElastic()
    }
}
