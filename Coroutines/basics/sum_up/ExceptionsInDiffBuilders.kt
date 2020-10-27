package basics.sum_up
import kotlinx.coroutines.*

// we use try and catch block to handle exception in coroutine just like we handle other exceptions
// or helper functions like runCatching() a built in function to handle exception. under the hood it uses try and catch block.

/** Different Coroutine builder throw exception in different way !**/
val scopeForTest= CoroutineScope(Job())
fun exceptionInLaunch() = scopeForTest.launch {
    // In Launch coroutine builder exceptions are thrown as soon as they occurs
    // so we can cover or wrap up the code that can throw exception to handle
    try {
        throw Exception("Some exception thrown !")
    }
    catch(e :Exception) {
        println("exception is handled !")
    }

}

// Lets look for async coroutine builder now
/** there are two cases for this when
 *  1. when async is root coroutine -: root coroutines are coroutine that are direct child of  CoroutineScope or SupervisorScope (supervisorScope)
 *  2. when async is not root coroutine
 *  **/
fun rootAsyncCoroutineEg1() {
    // In the first case -: when the async is the root coroutine
    // exception are thrown when we call await on its returned deferred Instance
    val deferred = scopeForTest.async {
        println("throwing exception !")
        throw Exception("You Called await() !")
        // exception not thrown here doesn't mean that unreachable code will get executed bellow ! ( its just unreachable coz at some point we are going to call await for the result ! )
        print("not reachable statement !")
    }
    println("No exception will be thrown")
}

suspend fun rootAsyncCoroutineEg2() {
    val deferred = scopeForTest.async {
        throw Exception("Some Exception ")
    }

    try {
        println("Throwing Exception !")
        deferred.await() // exception is thrown because we are calling await on deferred instance of async coroutine  which is a root coroutine !
    } catch(e: Exception){
        println("Handled !")
    }
}

// root async coroutine in supervisorScope
suspend  fun rootAsyncCoroutineEg3() = supervisorScope  {
    // exception will not be thrown here in async block !
    val deferred = async { // Async as direct child of supervisorScope
        println("throwing exception !")
        throw Exception("Some exception !")
    }
    // all the exceptions occurred inside async block will be thrown when we
    // call await on its returned deferred instance !
}

val supervisorScopeForTest = CoroutineScope(SupervisorJob()) // creating a supervisor Scope !
fun rootAsyncCoroutineEg4() {
    // async as as direct child of a Coroutine Scope Which holds a supervisorJob or we can just call it supervisorScope
    supervisorScopeForTest.async {
        println("throwing exception !")
        throw Exception("some Exception")
    }
}


// now lets look for not root async coroutine
suspend fun notRootAsyncCoroutine(): Unit = coroutineScope{
    // note that async is not "CoroutineScope" direct child !
    // its the child of "coroutineScope" (suspend function) and this will be called from some CoroutineScope means async will not be root coroutine of CoroutineScope
    // also here we are using or have normal job (Job) so the exception will be propagated up the scope in hierarchy
    // thus catch block doesn't work here !
    try {
        async {
            println("throwing exception !")
            throw Exception("Some Exception occurred !") // Exception wil be thrown here
        }
    } catch(e: Exception) {
        println("handled !") // wont handled here !
    }
}

fun main() = runBlocking<Unit>{

    // exceptions are thrown immediately !
    // exceptionInLaunch().join()


    // async as root coroutine in CoroutineScope
    // rootAsyncCoroutineEg1() // no exception is thrown ! ( because we are not calling await anywhere in this function declaration)
    // delay(2000) // to keep jvm alive !

    // rootAsyncCoroutineEg2() // exception is thrown and handled (because we are using await on deferred instance in this function declaration )
    // and using try and catch block to handle the exception !


    // async as root coroutine in supervisorScope
    // rootAsyncCoroutineEg3() exception is not thrown ( see function declaration)

    // async as root coroutine in CoroutineScope which holds or has supervisorJob.
    // rootAsyncCoroutineEg4() // exception will not be thrown because async is root coroutine in SupervisorScope (see function declaration)
    // delay(2000L) // tio keep jvm alive !

    // async as not root coroutine
    // notRootAsyncCoroutine() // exception will be thrown

}