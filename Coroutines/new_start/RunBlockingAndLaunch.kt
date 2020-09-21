package new_start

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * so lets start coroutine again
 *
 */

fun main () {

    // launching a coroutine in background of GlobalScope and continue.
    // GlobalScope is a CoroutineScope builder which provides coroutineContext

    // a coroutine builder that do not block the main thread
    GlobalScope.launch{ // means this coroutine will have life time of the whole program
        delay(1_000) // suspend function
        println("Thread: ${Thread.currentThread().name}")

    }
    println("launch is not blocking the main thread , so i will get executed first ! ")

    println("Thread: ${Thread.currentThread().name}")
    runBlocking { // a coroutine builder which blocks the main thread
        println("Main thread is blocked by the runBlocking coroutine builder")
        delay(4_000)
        println("Releasing the main thread now") // to keep my application running so that our coroutine can fully execute.
    }

    println("completed !") // runBlocking is blocking the flow or execution until specified time is over.



}
