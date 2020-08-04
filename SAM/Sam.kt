// SAM acronym for Single Abstract Method
// SAM : Interface that's has a single abstract method

// classic way to implement
class Program: Runnable{
    override fun run() {
        println("Classical way Running now.....")
    }
}

fun main() {

    // first Way
    val runnable  = object: Runnable{ // creating a object that implements SAM (Runnable)
        override fun run(){
            println("Way one...")
        }
    }
    runNow(runnable)

    // compiler will take care of everything
    val runnableTwo = Runnable{println("Way two ( Compiler will do all the stuff above)")}
    runNow(runnableTwo)

    // first creating the object and passing the object to function that requires SAM
    val runnableThree = Program()
    runNow(runnableThree)

}

// function that requires SAM (Runnable)
fun runNow(runnable: Runnable){
    runnable.run()
}