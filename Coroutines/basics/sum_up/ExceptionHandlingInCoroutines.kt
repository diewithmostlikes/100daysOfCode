package basics.sum_up
import kotlinx.coroutines.*

/**
 * if a coroutine throws a exception
 * it will get canceled and  propagate it to the  parent and parent do two things
 * 1. cancel rest of its children
 * 2. cancel it self and propagate exception to its parent.
 * **/
val scopeOne  = CoroutineScope(Job())
 fun exceptionTestOne() {
    val childJob  = scopeOne.launch {
        println("doing  something !")
    }
    childJob.cancel()
    // cancellation of a coroutine also throws a exception call Cancellation exception
    // but it doest effect its parent and other coroutines are not effected by it.

    scopeOne.launch {
        println("First Coroutine: Throwing exception !")
        throw Exception("did this on purpose")  // other coroutine and the root Coroutine will be effected ( canceled )
    }

    // these will be canceled as soon as above coroutine will get cancelled !
    scopeOne.launch {
        delay(1000L)
        println("Second Coroutine !")
    }

    scopeOne.launch {
        delay(3000L)
        println("third Coroutine !")
    }
}

// this is good for some case in which we want all the coroutine to be canceled if one of them has some issue or throw an exception.
// but what for other case scenarios when we don't want to cancel the  whole coroutine hierarchy.
// and just want to handle  and cancel the specific coroutine in which the exceptions has occurred

// we can provide a scope supervisor job.
// with supervisor job exception or failure will not effect other coroutines
// and supervisor job will not cancel it self

val scopeTwo = CoroutineScope(SupervisorJob())
fun exceptionTestTwo() {
    scopeTwo.launch {
        println("ha ha ha ha ha im gonna throw exception !")
        throw Exception("thrown by evil coroutine !")

    }

    // these coroutine are going to execute no matter the above coroutine has thrown an exception
    scopeTwo.launch {
        delay(1000L)
        println("coroutine two done!")
    }

    scopeTwo.launch {
        delay(2000L)
        println("Coroutine three done !")
    }
}

fun main() = runBlocking <Unit>{
    println("with normal job !")
    exceptionTestOne()

    println("with supervisor job !")
    exceptionTestTwo()

    delay(4000L) // to keep jvm alive (note above called functions are not suspend or the code inside those functions are not calling any other suspend function)
}