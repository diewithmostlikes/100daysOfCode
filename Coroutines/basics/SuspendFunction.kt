package basics
import kotlinx.coroutines.*

/**
 * suspend functions
 */

// so what is suspend function
// basically the function that is only callable from  inside the coroutines and other suspend function or can call other suspend function inside it.

/** what makes them different then normal function*/

// lets see example to understand them
fun explainSuspendFun(): Job {
    // lets create a coroutine and launch it
   return GlobalScope.launch { // here im  launching a coroutine in GlobalScope (explaining in Scope.kt file)
      launch {
          mySuspendFunction()  // and call our first suspend function.

          /** what it does is... tell the thread im a suspend function
           * and i can take some time to fully execute or to return something..
           * but don't waste time waiting for me i release you go and check for my brother coroutines and code lines and execute them
           * when the result well be ready or im fully executed, i will tell you and you can come back to me and resume where you left off and  get the result from me
           * and complete this coroutine where i have been called from. */

          // that's exactly what a suspend function do and we call all this process : non-blocking
          println(" Done !")
      }

       launch {
           println("above suspend function has not blocked  Thread but suspended coroutine for some time which mean this coroutine can be executed ! ")
       }

       println("Awesome Coroutine. ")
   }
}

suspend fun mySuspendFunction(): String {
    delay(3_000L) // can call other suspend function from inside other function.
    return "Done"
}
fun main() = runBlocking {
    val job  = explainSuspendFun()
    job.join() // to keep the jvm alive Note we are working with GlobalScope which has scope of whole application.
}
