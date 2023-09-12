package koroutine

import com.google.common.util.concurrent.RateLimiter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    val rateLimiter = RateLimiter.create(1.0)

    runBlocking(context = Dispatchers.IO) {
        repeat(1000) {
            launch {
                while (true) {
                    val acquisition = rateLimiter.tryAcquire()
                    if (!acquisition) yield()
                    else {
                        println("gotcha ! $it")
                        break
                    }
                }
            }
        }
        yield()
    }
}