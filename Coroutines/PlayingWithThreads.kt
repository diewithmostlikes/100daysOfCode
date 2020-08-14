import kotlin.concurrent.thread

// Threads in kotlin.
fun main() {

    // This is Synchronous code which  one command or line of code will execute after another by CPU
    // Or we can say lines of code is executed sequentially by the CPU
    printNumbers() // executed first
    printNumbersTwo() // executed after second
    println()

    // what if we want to execute or run our code or lines of code simultaneously
    // That's where asynchronous programing  comes in.

    /**Each Thread represents one execution flow*/
    // Lets create Three thread that executes simultaneously.
    thread(start = true, name = "myThread") {
        for (i in 1..10) println("Printing $i from Thread: ${Thread.currentThread().name}")
    }

   thread(start = true, name="myThreadTwo") {
       for(i in 20..30) println("Printing from $i from Thread: ${Thread.currentThread().name}")
   }

    // If each thread represents one execution flow it means in main() function we also have default thread
    println(getThreadName())
    (80..90).forEach{println("$it from ${Thread.currentThread().name}")}

    /** all of the above threads are running simultaneously.
     * Order of parallel println() results from above above threads are indeterminate, It depends on CPU */


    threadInsideFunction() // we are calling thread inside a function from our main thread which is default thread

    /**
     * SomeTime we need to manipulate many threads
     * and share data back and fourth between threads
     */

    // one more thing that  java threads are based on Os level threads and thus consume significant amount of system resources
    // and thus we can't create many many and many threads because at some point we will end up with OutOfMemoryError thrown by java .


}


// functions to print numbers
fun printNumbers() { for (i in 1..10) println(i) }
fun printNumbersTwo(){ for (i in 1..20) print(i) }


fun getThreadName() = "Thread name: ${Thread.currentThread().name}"

fun threadInsideFunction(){
    thread(start = true, name="threadInsideFunction") {
        (100..110).forEach{println("Printing $it from Thread ${Thread.currentThread().name}")}
    }
}


