import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

/**
 * Reflection is set of language and library features that allows introspecting of our own program at runtime
 * In kotlin properties and functions are first class citizens
 * we can Introspect them at runtime with by using the functional or reactive style such as knowing the name or the type of property or function at runtime
 */


/** class references */
// getting reference to a kotlin class at runtime by using : class literal syntax
class Air (val quality: Int = 10){
    fun someMethod() { println("hey im a method !") }
}

fun aboutAirClass() {
    val airClass = Air::class // class literal syntax

    // get collection of constructors of a class
    println("Constructors: ${airClass.constructors}")

    // get collection of properties of the class
    println("Properties: ${airClass.memberProperties}")

    // get collection of methods of a class
    println("Method: ${airClass.functions}")

    // get collection of members of the class (methods and properties)
    println("Members: ${airClass.members}")

    // we can do much more

}

// passing  Class reference as argument
fun createObjectOfClass(classRef: KClass<Air>): Air {
    return classRef.createInstance()
    // To do that class must have no arg constructor and if it do have args we need to provide default value to it
    // if we don't we will end up with an Exception
}
fun Air.test() {
    println("test !")
}

fun memberRef(){
    // we can also get reference to class members (property or method)
    val propRef = Air::quality
    val methodRef = Air::someMethod
}


fun main() {
    aboutAirClass()

}
