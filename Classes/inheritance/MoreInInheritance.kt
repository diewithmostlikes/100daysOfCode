package inheritance

// creating parent classes.
// classes are final by default.
// class must be declared as open if you want it to be inheritable.

open class ParentOne(val pOne: String, val pTwo: String) {


    // we can override the properties to do so we have to declare them open
    open val propThree: Int = 10
    open val propFour: String = "this is a string"
    open val propFive: Test = Test()
    open val propSix: Test = Test()
    open val propSeven: String = "this is also a string"
    open val propEight: Int = 100
    open var propNine:Int = 1000


    fun funOne () {
        println("default implementation")
    }

    open fun funTwo() { // We can override function or methods but must have to write open modifier to do so
        println("im open to override !!")
    }
}


open class ParentTwo {
    val propOne: String  = "this is a property"
    open val propTwo: String = " this is another property" // write open modifier if you want a property to be overridden in subclasses

    fun memberFun() {
        println("default implementation")
    }

    open fun memberFunTwo() {

    }
}

class SampleParentClass {
    open fun memberFunction(){ } // open modifier has no effect if we add it to the members of final class
}


// creating subclasses or derived classes
// if the child or derived class has primary constructor then base or parent class can(and must be)initialised here with parameters passed to primary constructor.
class ChildOne(param: String, paramTwo: String) : ParentOne(param, paramTwo) {


    // lets override the funTwo() method of the parent class
    // by overriding it will not use the parent or base class implementation of this method
    // instead it will use this implementation whenever this method is called from the instance of this class
    override fun funTwo() {
        println("overridden  this function")
    }

    // Note the override is itself open so funTwo() method can be overridden again in further subclasses
    // if you want to prohibit that you should use 'final' before the override modifier

    // just like the we can override the methods of the base or parent class we can also override the properties of base class
    // we can override the properties by using initializer or get() "getter".
    override val propThree: Int  = 10
    override val propFour: String = "overridden this property"
    override val propFive: Test = Test()
    override val propSix: SubTest = SubTest() // Overridden property Type must be the same or subtype of the base class property Type
    // overriding property using getter.
    override val propSeven: String
        get() = "overridden this property by getter"

    override var propEight: Int = 1000 // we can also override val property as var but not vice versa
    // Error -: override val propNine: Int = 1000000


}

// Note that the test class has no primary constructor or secondary constructor but still compiler generates a constructor without parameters for you
open class Test
class SubTest: Test() // and thus it has to be initialised here.

// if the Derived class have no primary constructor then each secondary constructor has to initialise the base type (using super keyword)
// or delegate to another constructor which does that.

class ChildTwo: ParentOne  { // does'nt have primary constructor.

    constructor(param: String, paramTwo: String): super(param, paramTwo) // initialising the base type in secondary constructor

    // delegating to the constructor which initialise
    constructor(param: String): this(param, "value to second parameter")

}

// Note we can also override the property of base or parent class in constructor by using override in constructor.
class ChildThree(override val propThree: Int = 1000): ParentOne("1", "2")









