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
    childJob.cancel() // when we cancel job( Job.cancel() ) or some failure occurs in coroutine will move the jon in cancelling state.
    // where isActive becomes false and isCancelled becomes true and then job moves to cancelled state where is completed becomes true.
    delay(1L)
    // well it takes Job a little time to move from cancelling to canceled state.
    // well its a very short time idk this situation is only occurring in my pc or it really takes some time to move from cancelling  to cancelled state
    // thus i delayed it for 1 millis  so the bellow isCompleted (childJob.isCompleted returns correct value)

    println("""
        isActive: ${childJob.isActive}
        isCanceled: ${childJob.isCancelled}
        isCompleted: ${childJob.isCompleted}
    """.trimIndent())


}
