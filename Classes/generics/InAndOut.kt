package generics

// Declaration-Site Variance
open class Base
open class Derived: Base()
class DerivedChild: Derived()

// 1.Covariant
class Container<out T>(val value: T){

    val propTest: T = value

    // T may occur in only out position - only return it from member functions and cant consume it
    fun whatsInsideTheContainer(): T  = value

    // Error -: fun consumeType(type: T) {} // we can't consume
}

// We can pass and store instance of Derived class in the reference of <Base>
fun referenceStoreTestOut(derivedContainer: Container<Derived>){
    // we can do this because T is in out position
    val baseContainer: Container<Base> = derivedContainer // Container<Base> can easily be SuperType of Container<Derived>
}



// 2. Contravariance
class StringRep<in T> {

    // T is in In Position we can only consume it and can't return it
    fun rep(type: T): String = type.toString()

    // Can not return type T that is in In Position  and thus it can be  consumed not returned or produced
    // Error - : fun getType(type: T): T =  type

}

fun referenceStoreTestIn(type: Base) {

    // we can store Base Type in reference of Derived Type if we have used in
    val stringRep : StringRep<Derived> = StringRep<Base>()

}


fun main() {
    val base: Base = Base()
    val derived:Derived = Derived()
    val derivedChild = DerivedChild()

    // In Covariance

    // passing Container<Derived> in reference of Container<Derived>
    val containerOne = Container(derived)
    referenceStoreTestOut(containerOne)

    // Also can pass subtype of Derived like Container<DerivedChild> in the reference of Container<Derived> - common behavior of function parameters
    // because behind the seen function parameters are in In Position
    val containerTwo = Container(derivedChild)
    referenceStoreTestOut(containerTwo) // Container<Base> can easily be super type of Container<DerivedChild> which is subtype of Container<Derived>


}