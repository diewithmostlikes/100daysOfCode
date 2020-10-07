package cancellation

import kotlinx.coroutines.*

// suppose a activity or fragment or a page launched a/some coroutines and user decided to close or navigate from that fragment or activity or page.
// now we don't need those coroutines running in background and also don't want there results
// and yeah coroutines running in the background has no where to return to because we have closed or moved from our activity or fragment or from a page.


// So in that case we can cancel it/them out
// lunch returns Job instance that can be used to cancel the coroutine who's job instance it was

suspend fun cancelableCoroutine()  = coroutineScope {
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

// so the above code  works perfect and can be canceled any and on time.
// but if you are performing some heavy computation
// there is nothing that can stop your coroutine from working or running

// take a look
suspend fun nonCooperativeCode() = coroutineScope{

   val job =  launch{

       var nextTime = System.currentTimeMillis()
       var i = 0
        while (i < 5) {
            if (System.currentTimeMillis() >= nextTime) {
                println("lol ${i++} !") // increasing i only if the current time is greater and equal to nextTime that we  will keep looking until the current time is greater.
                nextTime += 500 // printing 2 times in one sec
            }
        }
    }

    delay(1000)
    println("taking so much time... canceling..")
    job.cancel() // it just moves the coroutine to cancelled state and when the above heavy computation done then moves to the canceled state. :-(
    println("canceled !")
}

// so yup calling just cancel will not help you out in stop doing extra work..
// so what then
// to stop your heavy computational code immediately your code should be cooperative
// means when it has to check when the coroutine is active and not (doing work only when the coroutine is active )
// when we call cancel we the coroutine goes to the cancelling state and there isActive becomes false
// and we can use this to cancel or stop work in our code when isActive is false by checking for its value

// lets do it.
// you need to check for cancellation before  doing some long running or we can say heavy computation work
suspend fun cooperativeCode() = coroutineScope{
    val job = launch(Dispatchers.Default) {
        var nextTime = System.currentTimeMillis()
        var i = 0
        while(i <5 && isActive) {
            // ensureActive() : we can also use this if you dint want to check isActive in a condition
            // reduces boilerplate code but we can't clean up the resources below because
            // it throws Cancellation exception and the bellow and code outside the while becomes unreachable
            // and coroutine gets cancelled !
            if(System.currentTimeMillis() >= nextTime){
                println("double lol ${i++}")
                nextTime += 500
            }
        }
        if(!isActive){ println("cleanup") } // we can also do some clean up !
    }

    delay(1000)
    println("canceling....")
    job.cancel()
    println("canceled  !")
}

// perfect

// when we cancel a coroutine it throws a CancellationException and we can create one(CancellationException("message)) to provide some extra information.
// and it simply gets canceled without effecting the other coroutines hmmmm...
// and parent coroutine do nothing

// lets try to throw some another exception lets see what happens
suspend fun hehhehe() = coroutineScope {

    launch{
        delay(2000)
        throw IllegalStateException("just for fun...") // this will affect the whole scope and every launched coroutine and whole program
    }

    launch {
        println("working...")
        delay(3000)
        println("coroutine that is not aware... that something is going to happen.. :-P.")
    }
}

// todo yield




fun main() = runBlocking<Unit> {

    // cancelableCoroutine()
    // nonCooperativeCode()
    // cooperativeCode()
    // hehhehe()


}
