package basics.sum_up

import kotlinx.coroutines.*

/**
 * CoroutineJob -> Job -> is to handle Coroutine
 * new job instance is created and returned by async() or launch() when you create a new coroutine using them
 * */

suspend fun jobInstance() =  coroutineScope<Unit> {

    // these extension function of CoroutineScope returns job instance
    // that uniquely identifies the coroutine and manages its Lifecycle.
    val job  = launch {
        println("Returning job instance so if i took long or fail or at some point you can handle that situation ! ")
        delay(3000L) // Some work going...
        println("I'm done !")
    }

    val deffer = async{
        println("Returning the Deffer instance which is also a Job because Deffer Interface extends Job Interface ")
        delay(3000) //some work going...
        println("I'm done !")
    }

    launch   {
        delay(1000)
        println("Canceling Coroutines..")
        job.cancel()
        deffer.cancel()
        println("Canceled !")
    }

    // and we can also pass Job instance while creating new CoroutineScope. For eg.
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

}

fun main() = runBlocking<Unit> {

    jobInstance()
    jobLifecycle()

}

suspend fun jobLifecycle () = coroutineScope {

    // job can go through many states like new, active, completing,  completed, cancelling, cancelled.
    // well we don't have access to them but we can  know which state our job or coroutine is by accessing some Job's properties
    // like -: isActive,  isCanceled, isCompleted.
   val childJob =  launch{
       println("Working....")
       delay(2000L)
       println("Done !")
    }

    delay(1000L)
    childJob.cancelAndJoin() // when we cancel job( Job.cancel() ) or some failure occurs in coroutine will move the jon in cancelling state.
    // where isActive becomes false and isCancelled becomes true and then job moves to cancelled state where is completed becomes true.


    // now i used cancel and join because before i was only using cancel which is not a suspend function and the controller was immediately
    // executed the code below that's why i was having wrong value of the isCompleted because it takes some time for coroutine to move from
    // cancelling state to cancelled and my below code(for isCompleted) depends on the fully execution of cancel
    // that's why i need to suspend the coroutine until the cancel is done
    // we can do this by calling cancel() and then calling join() or we can simply use cancelAndJoin()
    // to cancel and suspend the coroutine until it gets fully canceled

    println("""
        isActive: ${childJob.isActive}
        isCanceled: ${childJob.isCancelled}
        isCompleted: ${childJob.isCompleted}
    """.trimIndent())

}
