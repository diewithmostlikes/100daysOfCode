package inheritance

// there are some common type inheritances.

// single level inheritance inheritance
open class Human(val age:Int, val height: Int, val weight: Int) {
    init{
        println("human class is initialising !")
    }
    // declaring Member functions or methods (common action that human can do)
    fun talk() { println("hello how are you") }
    fun walk() { println("im walking") }
    fun run() { println("im running") }
    fun eat() { println("i'm eating") }


}

// here we are inheriting only one class this is called single level inheritance
open class Person(val name: String,  age: Int,  height:Int, weight: Int): Human(age, height, weight){

    // person class can access properties and methods of the base class
    init {
        println("Person class is initialising....")
    }

    fun intro(){ println("Hi, My name is $name.I'm $age years old.") }
    fun gotoWork() { println("Going to work !") }
    fun study() { println("studying.........") }
}

// Multi-level inheritance
// but if i write one more class bellow and inherit Person class which is already inheriting Human class
// then it will become multi-level inheritance.
// for eg lets make it multi level inheritance by inheriting Person class

class Child(name: String, age: Int, height: Int, weight: Int):
        Person(name, age, height, weight){

    // Since the Child class  is inheriting Person class which is inheriting human Class
    // thus child class can access both Peron and Human Classes properties and Methods...
    fun eatFood(){eat()} // accessing human method
    fun aboutMe(){ intro() } // accessing person method
    // same for the properties we can accesses both classes properties.


    init {
        println("Child class is initialising ")
    }
    fun cry() { println("im crying......") }
    fun play() { println("Im having fun playing  ") }

}

// we call multi-level inheritance for this type of inheritance
// where a Class inherits a Class which is also inheriting a class.

fun main(){
    // lets see which class is initialised first
    val childObj: Child  = Child("kotlin", 12, 5, 25)
    // On object creation of a class if it is inheriting some class then the super or base class is always initialised first before derived class


}