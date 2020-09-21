package basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// works as an adaptor to start top level main Coroutine
// and to wrap the execution of the main function.
// we can also use it for unit testing,  like testing of suspend function.
fun main () = runBlocking<Unit>{ // blocks the main thread until coroutine inside it gets completed !

        launch{ // non blocking coroutine builder
            println("first coroutine inside runBlocking !")
        }

        // runBlocking will wait for this coroutine tio get completed !
        launch {
            delay(2_000L)  // code inside this coroutine block takes time
            println("Second coroutine inside runBlocking !")
        }

        // but if a coroutine has GlobalScope which in this case it is runBlocking it self
        // so when the run blocking knows every coroutine got completed it will release the main thread !
        // and as the coroutines with GlobalScope has the Scope of runBlocking it will have to be fully completed before runBlocking gets completed.
        // other wise it will not get fully completed !
        GlobalScope.launch {
            println("i will take more time then runBlocking..... ")
            delay(4_000L) // some code taking time
            println("Coroutine with GlobalScope is completed !")
        } // runBlocking will look at it as : okay so u have my scope so "if i die... you die..."
        // or we can say runBlocking won't wait for it to get completed

        launch {
            println("Third Coroutine inside runBlocking !")
        }

    // we can delay the time for runBlocking to complete so that child coroutine with GlobalScope  get completed !
    // First its not a good approach to do that
    // Secondly in real case scenario we don't know how much time a coroutine is going to take to get completed !
    // and lastly it will block the flow or execution so anything below it has to wait for the specified time !

    // so lets just delay the time here for this time.. and i will cover up how to handle this situation in Job.kt File
    delay(5_000L) // we have to wait for at more then 4 sec because child coroutine with globalScope here is taking 4 sec to get completed !
    // remove it to see a parent's harsh behavior to his children :-)

    println("runBlocking Completed !") // has to wait until the delay is over !

}

