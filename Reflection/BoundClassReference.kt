/**Bound class reference*/
// Getting  a class reference from  a specific object
// also by using ::class syntax by using the object as receiver( objectAsReceiver::class)

open class Base
open class Derived: Base()

fun boundedClassReference() {
    val name: String = "Kotlin"
    println(name::class.qualifiedName)

    // what if we get a reference of derived class in the reference of base class
    // what would be the class reference of it. is it base class reference or derived class reference lets find out
    val obj: Base = Derived()
    println(obj::class.qualifiedName) // so yup we get the exact class reference of the object despite the type of the receiver.

}

fun  main() {
    boundedClassReference()

}

