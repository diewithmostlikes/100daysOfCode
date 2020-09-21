package basics
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * so what job ?
 * basically the job is anything that can be canceled ( any cancelable thing ) or its basically a background job
 * and has states like active, canceled, completed  etc and can be started and completed !'
 * */
fun main() = runBlocking<Unit> { // blocking main thread

    val job  = GlobalScope.launch {
        println("hey im running please wait for me i will take some time  !")
        delay(10_000)
        println("Im done you can move on now")
    }

    // so instead of delaying we use .join() method of the job to wait for  completion of coroutine on whose job we have called .join() method in a non blocking way !

    job.join() // wait for the GlobalScope coroutine to get completed
    // well if we need to join or end or complete runBlocking when all its children gets completed..
    // then i see no purpose of launching a coroutine with global scope
    // we can just do it with the launch { } coroutine builder
    // but that's just for example here !

    // now our code of the main coroutine is not tied to duration of the background job !
}

// just for example
fun jobCancelExample() {

    val job = GlobalScope.launch { // non blocking coroutine
        delay(3_000L) // some code running or waiting for a response.
        println("returning you instance of job !")
    }

    // canceling the job ! (cancel execution coroutine)
    val resultCancel = job.cancel()
    println("Canceling execution of coroutine: $resultCancel")

}