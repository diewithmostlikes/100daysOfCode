package basics.coroutine_builders

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/** withContext ->  Similar like async when you need result of the execution of a coroutine */
//
// so with context is similar like Async but it is optimized for straightforward cases.
// async coroutine builder which returns a instance of Deffer but
// withContext coroutine builder only returns the result of the execution(whatever you want to return from its lambda block.)
// we can use it when we want the  result itself but not the Deferred instance.

/** Some more things to notice here
 * 1. its a suspend function -> soo no need to call methods like await or join on it to suspend coroutine until we get the result.
 * 2. its context parameter has no default value unlike there were in Async and Launch functions of CoroutineScope, so we have to define it by ourselves.
 * 3 it returns whatever we return from its lambda block. */

// lets do it
val database = JustStringDB.getInstance()

suspend fun insert(string: String) = withContext(Dispatchers.IO) { // launch this coroutine in background thread (pool of threads)

    database.insert(string)
    // In reality or in actual scenario database operation usually take some time
    // that's why we have used Dispatchers.IO which used shared pool of threads for this type of long running tasks.
    // so our main or UI thread can run smoothly..

}

suspend fun getRecentlyAddedString(): String = withContext(Dispatchers.IO) {
    database.recentlyAdded()
}

suspend fun getAllStrings(): List<String> = withContext(Dispatchers.IO) {
    database.getAllStrings()
}

suspend fun delete(string: String) = withContext(Dispatchers.IO) {
    database.delete(string)
}


fun main() = runBlocking<Unit> {

    launch {
        println("User loves Smooth UI..")
    }

    val someData = listOf("kotlin", "google", "jet brains", "java", "python")
    val job = launch {
        someData.forEach { insert(it) } //  Suspended until the result is ready and returned
        println("insertion Done !")
        val lastItem = getRecentlyAddedString()
        println("Lastly Added: $lastItem ")
    }.join() // so we can't launch the below coroutine before this coroutine is done.


    launch {
        println("All Data: ${getAllStrings()}")
    }

}


// fake database
// well there is no way to create a singleton generic class in kotlin so  i have to do it like this (ofCourse can be improve.)
// i can use "object" here but i ws just practicing singleton.

class JustStringDB private constructor() {
    private val _dataset = mutableListOf<String>()
    val dataset: List<String>
        get() = _dataset


    fun insert(string: String): Boolean {
        _dataset.add(string)
        return true
    }

    fun recentlyAdded(): String = _dataset[_dataset.size - 1]

    fun getAllStrings(): List<String> = _dataset
    fun delete(string: String): Boolean = _dataset.remove(string)


    companion object {
        @Volatile
        private var INSTANCE: JustStringDB? = null

        fun getInstance(): JustStringDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = JustStringDB()
                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}