/**Extension Function
 * Sometime we want or wish if particular class that we don't have access to modify has some a function or method
 * Or sometimes we write function or method that work with a particular Class Type
 * And these scenario we can use extension functions
 * */

// Lets start now
// I Wish Like Python a string can be multiplied with integer and return you a multiplied number of String
// Let's use Extension function

// well we can  declare simple function for that..
fun multiplyStringBy(string: String, times: Int): String {
    var multiString = string
    for( i in 1..times) multiString += string
    return multiString

}

// but science we know this function is only for strings or only work String types
// we can make it more kotlin way.. by using Extension function


fun String.multiply(times: Int): String { // left side - class on which our extension function will work
    var multipliedString: String = this // we can access class public members like method or properties by using "this"
    // note we can only access the public members not the private members
    // or extension function can only use visible members of the class..

    for (i in 1..times) multipliedString +=  this // Added the string to multipliedString by times Times...
    return multipliedString // returned the multipliedString
}

/**
 * Note by using extension function we are not adding function or method to the class or we are not modifying the class or instance.
 * Behind the seen extension function get compiled down to the regular function, that takes target instance as the parameter
 */


// if a class already have function with same name that we give to our extension function
// the member method will be called not the extension function
class ExtensionTest {
    fun memberMethod() {
        println("Im the Member function or method")
    }
    companion object { }
}

// lets create the extension function with the same name
fun ExtensionTest.memberMethod() {
    println("this is the extension function")
}

// but we can overload the method by using the extension function
fun ExtensionTest.memberMethod(string: String){
    println(string)
    println("Method overloading is possible !")
}

/**
 * We can also define or declare extension function for Companion object of a class.
 */

fun ExtensionTest.Companion.companionMethod(){
    println("extension function also work with companionMethod")
}

/**
 * Just like we can declare Extension method or function to the class..
 * We can also declare extension properties...
 */

val ExtensionTest.propOne: Int
    get() = 10
// Error : val ExtensionTest.propTwo: Int = 10
// This is because the extension don't insert members into the class,
// so there is no efficient way for an extension property to have backing field
// and thus we can only provide its getter implementation..

fun main() {
    // So we have declared our extension function lets use it.
    val langName: String = "Kotlin"

    // using our extension function by calling it on String types
    val multipliedName: String = langName.multiply(10)
    println("*".multiply(50)) // now we can call our multiply() extension function to every string type.
    println(multipliedName)
    println("*".multiply(50))

    // but what if we call it on nullable String type
    val mayBeNull: String? = null
    mayBeNull?.multiply(10) ?: println("The string is Null")
    // we can handle it by using '?' operator

    // calling memberMethod() method of ExtensionText class
    val extensionTestObj = ExtensionTest()
    extensionTestObj.memberMethod() // Actual method (ExtensionTest.memberMethod()) will be called not the extension function.
    extensionTestObj.memberMethod("Koltin") // calling  overloaded method declared by extension function.

    // Calling extension function  which can be called directly on the class without creating instance
    // because we have declared extension function on the companion object Of class (ExtensionTest)
    ExtensionTest.companionMethod()

    // calling extension property
    println("Property declared with Extension property: ${extensionTestObj.propOne}")

    // lets call extensionMethod() on Class A instance
    val objA: A = A()
    // Error -: objA.extensionMethod()
    val objB: B = B()
    // Error : objB.extensionMethod()
    // Error : objB.prop.extensionMethod()

    // so we can access a member extensions only inside class
    // or we can create a helper method that calls it inside the class
    objB.callExtensionMethodOfA()

}

// Declaring extensions as member in class
class A  // empty class

class B{
    val prop: A = A()

    fun A.extensionMethod() { // accessible only on instances of A inside the class B
        println("extension function on A but also the member of B")
    }

    fun callExtensionMethodOfA() { prop.extensionMethod() } // Created this method so we can access it outside the class


}









