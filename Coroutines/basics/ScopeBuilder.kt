package basics

import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =  runBlocking<Unit> {
    launch {
        delay(300L)
        println("Children of runBlocking !")
    }

    // So Different coroutine builder provides a CoroutineScope
    // or Instance of CoroutineScope to its scope of the code block.

    /** But we can declare our own scope using CoroutineScope Builder*/
    // CoroutineScope is suspending function means it wont block the current thread, it suspend releasing the underlying thread for other use.
    // it creates a coroutine scope and doesn't complete until all launched children complete.
    coroutineScope  { // suspend function.
        launch{
            delay(600L)
            println("Children of coroutineScope !")
        }
        delay(100L)
        println("Some Other code inside coroutineScope")
    }

    println("Some code inside runBlock !") // will not reach here because until the coroutineScope is suspend function and complete when its all children complete !

}

// runBlocking blocks the thread and wait for its children to complete.
fun runBlocksTheCurrentThread() {
    runBlocking {
        println("Thread: ${Thread.currentThread().name}")
        launch{ delay(5_000)}
    }
    println("Please runBlocking release the thread ! ")
}
