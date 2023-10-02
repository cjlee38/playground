package koroutine

import com.google.common.util.concurrent.RateLimiter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool

fun main() {
    val forkJoinPool = ForkJoinPool(1)
    forkJoinPool.submit {
        println("main task 1 started at ${Thread.currentThread()}")
        forkJoinPool.submit {
            println("subtask 1-1 finished at ${Thread.currentThread()}")
        }.get()
        println("main task 1 finished at ${Thread.currentThread()}")
    }.get()

    val executorService = Executors.newSingleThreadExecutor()
    executorService.submit {
        println("main task 2 started at ${Thread.currentThread()}")
        executorService.submit {
            println("subtask 2-1 finished at ${Thread.currentThread()}")
        }.get()
        println("main task 2 finished at ${Thread.currentThread()}")
    }.get()

//    val rateLimiter = RateLimiter.create(1.0)
//
//    runBlocking(context = Dispatchers.IO) {
//        repeat(1000) {
//            launch {
//                while (true) {
//                    val acquisition = rateLimiter.tryAcquire()
//                    if (!acquisition) yield()
//                    else {
//                        println("gotcha ! $it")
//                        break
//                    }
//                }
//            }
//        }
//        yield()
//    }
}
