package basics.sum_up

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// so far we have seen launch(), async(), withContext() .etc methods to launch coroutine.
// but on what object we are calling them on
// if we look at their declaration of them they almost look like this

// well i tried my best to fit them all in this example
// note they are the extension function of CoroutineScope that's why they we can only launch a coroutine by using any coroutine builder  on CoroutineScope
fun CoroutineScope.sample(context: CoroutineContext = EmptyCoroutineContext,
                          start: CoroutineStart=CoroutineStart.DEFAULT,
                          // block marked with suspend so we can use other suspend function inside it.
                          block: suspend CoroutineScope.() -> Unit /* replace with what they return respectively im taking job example*/): Job /* replace respectively */{

    val job = Job() // new jon instance is created by two of them like launch and async, so we can handle each and individual coroutine.
    // check the job.kt file inside this package to find what job is for.

    // to create a new CoroutineScope we need CoroutineContext
    val scope = CoroutineScope(context + Job()) // creating a coroutine scope
    // context here can be your passed CoroutineContext or the context of the parent coroutine or CoroutineScope that we Passed when creating instance of that CoroutineScope.
    // check CoroutineContextExplained.kt file for explanation on CoroutineContext

    launch { // im not sure about this yeah to run the block which is suspend thus we need a coroutine or another suspend function to run it so im taking launch here.
        block(scope) // they provide the CoroutineScope inside the block so that we can use that scope to launch more coroutine inside a coroutine
        // and they provide coroutine scope to there block and so on..
    }
     return job // return if they return something (like job or Deffer or the actual result
}
// Note all this is just for explaining how things should have worked together

// note runBlocking is providing (instance of) CoroutineScope to its block
// we haven't passed anything to context argument or parameter  that runBlocking wants
// because the Default value is give to it at compile time we not specify any.
fun main() =  runBlocking <Unit> {
    sample(coroutineContext) {
        delay(1000)
        println("yeeeeeee we created our own Coroutine Builder")
    }

}

// next -> CoroutineContextExplained.kt