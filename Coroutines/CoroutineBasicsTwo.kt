import kotlinx.coroutines.*

fun main(){
    exampleSix()

}

/**  Although they are light weight but they still consume some memory*/
// Prob1  -: what if the code of coroutine hang (erroneously delay )
// Prob2 -: What if we launch too many coroutine and ran out of memory
// Manually keeping references of all the launched coroutine and joining them is error prone

/** Every coroutine builder adds instance of CoroutineScope to the scope of code block*/
// runBlocking coroutine builder is adding instance of the
fun exampleFive() = runBlocking{
    // instead of  launching our coroutine in global scope
    // we can launch a coroutine in a specific scope of operation we are performing.

    launch { // now we don't need to join them explicitly
        delay(1000L)
        println("kotlin !")
    }

    println("Hello")
// runBlocking does'nt complete until all the launched coroutines in it get completed.
}


//  We can also use coroutineScope Builder to get our own scope. without using default builder
fun exampleSix() = runBlocking {
    launch{
        delay(200L)
        println("runBlocking child Coroutine ")
    }

    coroutineScope {
       launch {
            delay(500L)
            println("Child Coroutine of coroutineScope builder")
        }

        delay(100L)
        println("runs before the launch")
    }

    println("inside runBlocking..")
}

// The runBlocking and coroutineScope they both wait for their body and launched children to complete
// The main difference in between them is
// ==> runBlocking blocks current thread for waiting
// ==>  coroutineScope just suspends , releasing the underlying thread for other usage


