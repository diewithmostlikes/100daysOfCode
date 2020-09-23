package basics
import kotlinx.coroutines.*


// note this file can contain wrong explanation or code because im working on it !
/**
 * so what job ?
 * basically the job is anything that can be canceled ( any cancelable thing ) or its basically a background job
 * and has states like active, canceled, completed  etc and can be started and completed !'
 * */

private lateinit var job: Job

// passing job  to context so we can cancel main coroutine and all its children using that job instance !
fun main() = runBlocking<Unit>() { // blocking main thread // Main Coroutine

    // Note every coroutine builder adds an instance of CoroutineScope to the  scope of its code block

     job = doSomeThing() // getting job

    handleCancelException(job) // adding exception handler to job which is thrown when we cancel job

    job.join() // to launch coroutines in background thread and .join() to wait for the parent coroutine to  done working


}

suspend fun doSomeThing(): Job = GlobalScope.launch{
    launch { // launched in background (also has the scope of whole application
        delay(3_000)
        println("new coroutine  !")
    }

    launch {
        delay(7_000)
        println("Another coroutine  !")
    }

    launch {
        // lets say i want to cancel  the main and all its running or launched coroutines if the time exceeds more then 5 sec !
        delay(5_000)
        println("Ending the program ! ")
        endProgram()
    }
}

suspend fun endProgram() {
    //job.cancel() // to cancel all the jobs  or coroutines inside the main coroutine
    // it will through an JobCancellationException
    // we can handle that exception by setting up a listener on job
    // job.invokeOnCompletion { }

    // lets through my CustomException
    job.cancel(CancellationException("Program is taking longer then 5 sec"))

}


fun handleCancelException(job :Job) {
    job.invokeOnCompletion {
        if (it?.message == null && it?.message == "" ) { // there a function to this kind of condition i forgot it now will have to look for it !
            println("Unknown Exception !")
        }
        println("Job is canceled due to Reason: ${it?.message} ")
    }

    // i will practice how to handle exception when cancelling job in handlingException.kt file //
}

// waiting for job
suspend fun waitForJob() {
    val job  = GlobalScope.launch {
        println("hey im running please wait for me i will take some time  !")
        delay(3_000)
        println("Im done you can move on now")
    }

    // so instead of delaying we use .join() method of the job to wait for  completion of coroutine on whose job we have called .join() method in a non blocking way !

    job.join() // wait for the GlobalScope coroutine to get completed
    // well if we need to join or end or complete runBlocking when all its children gets completed..
    // then i see no purpose of launching a coroutine with GlobalScope
    // we can just do it with the launch { } coroutine builder
    // but that's just for example here !

    // now our code of the main coroutine is not tied to duration of the background job but its waiting for it to complete.

    // also having to keep reference manually to all the launched coroutine and join them is error prone.
}

// just for example
fun jobCancelExample() {

    val job = GlobalScope.launch { // non blocking coroutine
        delay(3_000L) // some code running or waiting for a response.
        println("returning you instance of job !")
    }

    // canceling the job ! (cancel execution coroutine)
    val resultCancel = job.cancel()
    println("Canceling execution of coroutine: $resultCancel")

}

