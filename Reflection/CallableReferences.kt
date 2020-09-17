/** references of function, properties and constructor can also be called or used as instance of function type */
// getting function reference and calling them

fun sayHello(name: String) {
    println("hello, I love $name")
}

// for illustration
val nameList: List<String> = listOf("kotlin", "java", "google", "jet-brains", "github")

// reference of function
fun functionReference() {
    // getting sayHello() function as function type value

    val ref = ::sayHello // taking function reference (KFunction1(String, Unit)
    // function references belong to the subtype of KFunction<Out R> Depending on the parameter count

    // and pass this function type value to another function or lambda
    giveMeAFunctionThatTakesAndReturnHaha("java", ::sayHello)

    // can also use in lambda
    nameList.forEach(::sayHello)

    // we can call the function by using .invoke() method or simply using parenthesis
    ref("google")
    ref.invoke("Kotlin")

    // also works with the overloaded function where expected type is known from the context
    nameList.forEach(::printSomething)
}

/** Reference of Member of Class or Extension Function */

// adding Pause() a extension function to MediaPlayer class
fun MediaPlayer.pause() {
   if (isPlaying) {
       isPlaying = true
       println("Paused !")
   }
}

fun extensionRef() {

    // if we get a reference of extension function in a variable then  we will have additional parameter accepting a receiver object
    val extensionFunctionRef = MediaPlayer::pause

    // so if i call it then we have to pass the object of the class that our Extension function is extending
    val myPlayer = MediaPlayer()
    // Error -> myPlayer.extensionFunctionRef() : we can't call it directly on the the object of class on which our extension function applied
    extensionFunctionRef(myPlayer) // need to Pass the object of the class or we can say we can use it like normal functions

    // if we want to have function type with receiver we need to explicitly state that..
    val extensionFunctionRefTwo: MediaPlayer.() -> Unit  = MediaPlayer::pause // now we have reference  of the function with receiver

    // so we can call this function reference directly on  the object of the class on which our Extension function is.
    myPlayer.play() // just playing it to let you know the code below works
    myPlayer.extensionFunctionRefTwo()

}

fun main() {

    functionReference()
    extensionRef()

}

// for simulating examples..
fun printSomething(number: Int) { println(number) }
fun printSomething(string: String) { println(string) }
fun <T> giveMeAFunctionThatTakesAndReturnHaha(arguments: T, function: (T) -> Unit ){  function(arguments) } // sound scary !
class MediaPlayer() {
    val trackName: String = "none"
    var isPlaying = false

    fun play() {
        isPlaying = true
        println("Playing !")
    }

    fun stop() {
        isPlaying = false
        println("Stopped !")
    }
}