package basics.coroutine_builders

import kotlinx.coroutines.*


// So there are many cases where you want a result back from a coroutine.
// launch returns Job instance but we want a result from a coroutine not a job instance.
// we can do so by using async coroutine builder which returns Instance of Deferred  containing and our result
// Deferred instance is also a job because Deferred interface inherits from job interface and contains result of the execution.
/** async
 * and we can use await to wait for the result of the coroutine (basically suspending the coroutine using await() until the result is ready */

suspend fun loginAs(username: String, password: String) = coroutineScope<Unit> {
    val authCoroutineJob = launch {
        // note async is a normal function like launch which don't suspend the coroutine on which they are being called..
        // we use .join() and await() on launch and async respectively to  suspend the coroutine in which they are being called .
        val user: User? = async { getUser(username, password) }. await() // suspending the the coroutine until the result is ready
        // note when we use await on async it will return you the actual instance that you returning from block of async instead of Deferred instance.

        // below coroutines will launched when above coroutine returns the result or Deferred Object
        launch {
            if(user != null) {
                println("\rHello, ${user.name} welcome back ! ")
                println("your are ${user.age} years old !")
            }else {
                println("Incorrect username or password ! ")
            }
        }
    }
    launch {
        print("Loading")
       while(!authCoroutineJob.isCompleted) {
           print(".")
           delay(500)
       }

    }

}


fun main() = runBlocking<Unit>{

    loginAs("google01", "1234password") // you should update your password dude.


}

// simulating fake request
data class User(val name: String, val age: Int)
// fake method to get data !
suspend fun  getUser(username: String, password: String): User? {
    delay(2000) // getting data from server usually takes some time.
    return when {
        username == "kotlin01" && password == "password1234" -> User("Kotlin", 4 )
        username == "google01" && password == "1234password" -> User("Google", 22)
        else -> null
    }
}