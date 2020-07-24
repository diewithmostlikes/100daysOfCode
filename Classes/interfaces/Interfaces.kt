package interfaces

// so now we know about abstract classes
// lets see what interfaces do and how it is different from abstract classes

// creating interface Rocket
interface Rocket{ // Interface don't have constructor
    // now every rocket have length but we can't define a default length  of  rocket (it can be big, small or medium in size)
    // so we have to make sure that every Rocket  should implement that.
    val length: Int // every thing is abstract by default in interfaces

    // interface can't hold a state because there is no backing field
    // Error val prop: Int = 10
    // but we can implement getter... for eg...
    val prop: Int
        get() = 10

    // abstract method fly
    fun fly()

    //  method that has default implementation
    fun funOne(){
        println("this is called default implementation")
    }
}

// lets create large Rocket
class LargeRocket: Rocket {


    // initialised the field or property length
    override val length = 100

    // implemented the fly method
    override fun fly() {
        println("count Down Started ")
        for (i in 10 downTo 1) {
            println(i)
            Thread.sleep(1_000)
        }
        println("Ignition started..")
        println("Rocket is going up stright....")
        println("Rocket has successfully launched..  ")
    }
}

// lets see how many interfaces can a class implement
interface InterOne
interface InterTwo
interface InterThree
interface InterFour

class ImplementInterfaces: InterOne, InterTwo, InterThree, InterFour
// so yup we can implement any number of interfaces in concrete class

// we can also implement abstract and interfaces together
abstract class Abs1
abstract class Abs2
abstract class Abs3

class ImplementBoth: InterOne, Abs1()
// but note we can only implement one abstract class at a time
// Error class Explain: InterOne, Abs1(), Abs2()
// or we can say a class can implement only single abstract class
// or a class have only one supper type
// but we can  implement as many interface we want


fun main() {

    val largeRocketOne: LargeRocket = LargeRocket()
    largeRocketOne.fly()

    // fun fact
    // we can hold a object of class that implements a interface in Interface reference or type
    val factOne: Rocket = LargeRocket()
    // but you can only access or do things that are defined in the interface only with this reference

}