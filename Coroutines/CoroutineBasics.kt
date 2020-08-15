import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main() {

    exampleFour()


}

fun exampleOne(){
    /**Coroutine are light weight threads.*/
    // They are launched with launch coroutine builder in context of some scope.

    // launching our new coroutine with GlobalScope
    GlobalScope.launch { // GlobalScope meaning the life time of this Coroutine is the life time of whole application

        delay(1_000) // non delay for one sec
        println("Kotlin")

    }

    println("hello")
    Thread.sleep(2_000) // To keep jvm Alive !
    // since Our New Coroutine is created with Global Scope it has life time of main Thread life time.


    // we can achieve same result by replacing
    // GlobalScope.launch{ } with thread { }
    // and delay() by Thread.sleep()

    thread {
        // delay is a suspend function
        // delay(1000) // Error because suspend functions are only allowed to be called from another suspend function or coroutines
        // delay is special suspending function that don't block the thread but suspend the coroutine
    }

}

fun exampleTwo() {
    // we have used blocking Threads.sleep() and non blocking delay()
    // it can be confusing which one is blocking and which one is not !

    // For this we can use runBlocking coroutine builder
    GlobalScope.launch { // launching new coroutine in background and continue..
        delay(1_000L)
        println("Kotlin")
    }


    println("hello Again !") // main threads also working...
    runBlocking{ // this will block the main thread. until whatever is inside is fully executed
         delay(2_000L) // To keep the jvm alive because the Above Coroutine is of GlobalScope and wont execute properly if our main thread done executing
        // and thus our GlobalScope coroutine has to be executed before our main thread done executing everything in it.
    }
}

fun exampleThree() = runBlocking{
    // more idiomatic way to use runBlocking on a particular thread
    GlobalScope.launch{
        delay(1_000)
        println("Kotlin !")
    }

    println("hello !${Thread.currentThread().name}")
    delay(2_000)
}

fun exampleFour() = runBlocking {

    // but its not a good way to stop or delay a thread by some time when our coroutine is working
    // because we don't know how much time the code is going to execute inside the coroutine
    // we can wait for the child coroutine to complete by using join

    val childCoroutine = GlobalScope.launch{
        delay(2_000)
        println("kotlin !")
    }

    val anotherChild = GlobalScope.launch {// this is another child coroutine
        delay(4_000)
        println("Another child !")
    }

    println("hello ")
    childCoroutine.join() // The thread that calls exampleFour() function will wait for the childC


}