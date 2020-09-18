import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

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

/**property reference*/
val name  = "kotlin"
var number = 10
fun propRef() {
   val propRef: KProperty0<String> = ::name // returns subtype of KProperty<T>

    // get property value
    println(::name.get())

    // get name of property.
    println(::name.name)


    // for mutable properties subtype of KProperty<T> has setMethod
    ::number.set(100)
    println(number)


    // can be used where a function with single generic parameter is expected
    val coolList = listOf("this", "is", "cool")
    val lengths =  coolList.map(String::length)
    println(lengths)

    // access property -> member of class
    val propRefTwo = MediaPlayer::trackName
    println(propRefTwo.get(MediaPlayer())) // need to pass the receiver object or object of referenced property class

    // extension function or property
    val player: MediaPlayer = MediaPlayer()
    println(player::trackNameWithNumber.invoke())
    // or
    println(MediaPlayer::trackNameWithNumber.invoke(player))


}

/**Constructor reference.*/
// constructor can also be referenced just like methods and properties

fun constructorReference() {
    val constructor = ::MediaPlayer
    val player = getMyPlayer(constructor )
    player.play()
}

fun getMyPlayer (constructor: () -> MediaPlayer): MediaPlayer {
    return constructor()
}

/** interoperability with java reflection */
class Tes (val test: Int)

fun ref() {
    val prop: Field? = Tes::test.javaField
    val method: Method? = Tes::test.javaGetter
    val javaClass = Tes::test.javaClass

    // or in java we can use .kotlin to get kotlin class
    val kotlinPropRef = Tes::test.javaClass.kotlin

}


fun main() {
    functionReference()
    extensionRef()
    propRef()

}

// for simulating examples..
fun printSomething(number: Int) { println(number) }
fun printSomething(string: String) { println(string) }
fun MediaPlayer.trackNameWithNumber() = trackName + "1"
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