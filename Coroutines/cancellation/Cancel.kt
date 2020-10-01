package cancellation

import kotlinx.coroutines.*

// suppose a activity or fragment or a page launched a/some coroutines and user decided to close or navigate from that fragment or activity or page.
// now we don't need those coroutines running in background and also don't want there results
// and yeah coroutines running in the background has no where to return to because we have closed or moved from our activity or fragment or from a page.


// So in that case we can cancel them all out
// lunch returns Job instance that can be used to cancel the coroutine who's job instance it was

fun main() = runBlocking {

    val job = launch {
        repeat(100) {
            println("Working.... $it")
            delay(500L)
        }
        println("Done")
    }

    delay(3000)
    println("Done Waiting for you !")
    job.cancel() // above coroutine will be canceled after 3 sec
    job.join() // suspending main coroutine until cancelled job is completed.

    // we can use job extension function cancelAndJoin() to cancel and join .
    println("code cooperated to be canceled !")

}
