package basics

import kotlinx.coroutines.*
// Threads are os level based and thus use significant amount of resources.
// they are easy to manage but until we have to handle many of them and we can not create so many of them and lead to Out of memory error.

// on the other hand coroutine are light weight
// and we can launch them as many as we want

suspend fun soMuchCoroutines() = coroutineScope {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(2000L)
            println("$it")
        }
    }
}

// Global coroutine are like daemon threads
// daemon threads are low priority threads that run in the background
// they don't prevent jvm from exiting no matter the daemon is running at that point.
// when all the non-demon threads done executing jvm terminates it self without even caring about demon threads
// if jvm find out a demon thread running it terminates it and shut it self down.

fun main () = runBlocking {

    val job = GlobalScope.launch {
        repeat(1000) { i ->
            println("Coroutine :  $i ...")
            delay(500L)
        }
    }

    delay(2000) // active coroutine that are launched in GlobalScope do ot keep jvm alive,  they like daemon Thread

}

