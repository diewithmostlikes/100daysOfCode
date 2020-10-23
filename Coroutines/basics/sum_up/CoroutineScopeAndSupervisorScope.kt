package basics.sum_up

import kotlinx.coroutines.*

/**
 * coroutineScope and supervisorScope is used to create sub scope with job or supervisor job respectively.
 * */

fun main() = runBlocking<Unit> {

    supervisorScope { // uses supervisorJob
        launch {
            println("Sad destroyer !")
            throw Exception("i have power to destroy my siblings but my parent not allow to do so :-(")
        }

        launch {
            println("double lol i wont get effected by the exception thrown by my siblings.")
        }
    }

    coroutineScope {
        launch {
            println("ha ha ha ha im gonna destroy my siblings")
            throw Exception("Destroying my siblings !")
        }

        // wont get run ever
        launch {
            delay(1000L) // leaving it no change to ever get to run
            println("i have terrible sibling (crying ..)")
        }
    }

}

// order matters here because if you run coroutineScope before supervisorScope will lead to a exception in main thread !
