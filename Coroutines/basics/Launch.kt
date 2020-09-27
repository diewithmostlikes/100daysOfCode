package basics

import kotlinx.coroutines.*

// launch is a extension function of CoroutineScope used to launch coroutines and also provides CoroutineScope inside in its lambda block (check declaration of launch.)

/** Launch takes there arguments or parameters
 *  1.CoroutineContext -> which hold information like Dispatcher and Job
 *  2.CoroutineStart -> specify when to start or launch coroutine
 *  3.block (function or lambda) -> work that you want done in a coroutine
 */


/**********************CoroutineStart**********************8*/
/** 1. CoroutineStart.DEFAULT */
/** 2. CoroutineStart.LAZY */

suspend fun defaultAndLazy() = coroutineScope {
    /* CoroutineStart.DEFAULT */
    // well by default "start" parameter of launch which take instance of CoroutineStart is set to CoroutineStart.Default
    // no need to explicitly set it because it is already set as default value of start parameter (its here just for illustration)
    launch(start = CoroutineStart.DEFAULT) { println("Launched or starts Immediately") } // coroutine will be started or launched immediately ( when control reaches to the launch function.)

    /* CoroutineStart.LAZY */
    // Coroutine will not ba launched until its necessary. we can do so by calling start() on corresponding job instance or object.
    val lazyJob = launch(start =  CoroutineStart.LAZY) { println("Aammmm.. im so lazy who launched me !") }
    launch{
        delay(2000L)
        println("Starting Lazy coroutine")
        lazyJob.start() // starting the above coroutine after delay of 2 sec
    }

}

/** a. CoroutineStart.ATOMIC (not completed !) */
// todo complete.
suspend fun atomicLaunch() = coroutineScope {
    /* CoroutineStart.ATOMIC */
    // its like CoroutineStart.DEFAULT, coroutine with this start launched immediately but coroutine is not cancelable
    val job = launch(start = CoroutineStart.ATOMIC) {
        delay(3000L)
        println("hahhaha you can't cancel me :-D")
    }
    job.cancel()
    println(job.isCancelled)
    GlobalScope.launch{}

}

/** b.CoroutineStart.UNDISPATCHED (not completed !)*/
// todo complete
suspend fun unDispatched() = coroutineScope {
    /* CoroutineStart.UNDISPATCHED */
    // Coroutine will be launched immediately until its first suspension point.
    launch(start = CoroutineStart.UNDISPATCHED) {
        println("line one !")
        println("line two !")

        suspendFunction() // suspension point

        println("line three !")
        println("line four !")

    }

}

suspend fun suspendFunction() {
    println("Suspend Function is suspend point for the coroutine ! ")
    delay(5000)
}


fun main() = runBlocking<Unit> {

    // lock runBlocking also provides CoroutineScope to its block (by simply making block (parameter that take function and makes it extension function of CoroutineScope)
    // that's why we are able to  call launch

    launch {/*..*/}
    // or we can say
    this.launch{/*..*/} // "this" here is the reference to the CoroutineScope Class and launch is extension function of it.
    // defaultAndLazy()
    unDispatched()

}