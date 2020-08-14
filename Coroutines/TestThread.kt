import kotlin.concurrent.thread


// i know its not the best example but just to practice my own and understand how the things are working..
fun main() {

    feelAwesome()

}


// Our app feels stuck when there is time taken by code or algorithms to run
var isExecuted  = false
fun printHelloWorld()  {
    Thread.sleep(10_000) // think of it as your code is taking time to run. and execute.
    print("\r") // \r removes whatever is printed on the line.
    println("hello World: ${Thread.currentThread().name}")
    isExecuted = true
}

// use this function if you want your program to feel stuck for sometime
fun feelStuck() {

    printHelloWorld() // wait for this function to execute
    println("Done !") // then this line will be printed.
}

fun feelAwesome () {
    // printing loading animation that user don't feel stuck on the program
    // at least now  he have some hope that  something is going on behind..
    thread(start = true, name = "Loading Thread") {
        print("Loading ")
        while (!isExecuted){ // loading animation until the printHelloWorld() is not executed
            for (i in 1..4) {
                if (i == 4) print("\b\b\b\b\b\b") // \b removes previous printed character on line
                else {
                    print(". ")
                    Thread.sleep(500)
                }
            }
        }
    }

    printHelloWorld()


}

