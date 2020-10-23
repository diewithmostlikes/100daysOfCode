package basics.sum_up

import kotlinx.coroutines.*

val scope = CoroutineScope(Job())
fun main () = runBlocking<Unit> {

    scope.launch {
        delay(4000L)
        println("something")
    }
    delay(500L)
    scope.cancel() // once scope get canceled it wont be able to launch coroutines.
    delay(500L)

    //
    scope.launch {
        println("not get launched because the scope is canceled !")
    }.join()

}