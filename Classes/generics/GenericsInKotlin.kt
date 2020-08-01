package generics

/**
 * Lets start with generics.
 */

// suppose we want to create a box that can hold a integers
class IntBox(val integer: Int)

// same for the Strings
class StringsBox(val string: String)

// for Float
class FloatBox(val float: Float)

// but that's a lot of work to define classes for each type that can store a specific hold a specific type

// That's where Generics comes into play

// Generic Class
// <T> is used to declare the class generic but we can also use any word instead of T (<Word>)
class Box<T>(val data: T) // data can be of any Type
// with this generic class we can  pass any Type to the primary constructor on object creation and now our box class can store any Types .

// lets create a class LiquidContainer that can hold some type of liquid (like water, patrol, juice. etc)

class JuiceLiquid(val liters: Int)
class PatrolLiquid(val liters: Int)
class WaterLiquid(val liters: Int)
class PaintLiquid(val liters: Int)

class WrongLiquidContainer <T>  (val liquid: T)  // T - we can pass all types and even null can be passed  .. There is no bound to T
/** In this generic class we can pass and store a different liquids but we can also pass and store the Int, String, or any other Type.
 * which we don't want here. because by default <T>  is like  <T: Any?> ( A nullable Any Type).
 * Means we can pass every type that is inheriting Any class  (which is every class basically..) and null value.
*/

// we can think it like as ...
class WrongLiquidContainerTwo<T: Any?> (val value: T)

/**Giving bound to type T */
// now as we want to store only  liquid in our liquid container.
// we can do it by giving  LiquidContainer generic class a Upper Bound

open class Liquid(val value: Int)
class Juice(liters: Int): Liquid(liters)
class Patrol(liters: Int): Liquid(liters)
class Water(liters: Int): Liquid(liters)
class Paint(liters: Int): Liquid(liters)

// This generic class is  bounded To only accept Liquid Type and the Subtypes of it
class LiquidContainer<T: Liquid> (liquid: T)
// Now we can only pass or store Liquid Types and its SubClasses Types


fun main() {
    // we can pass every type to the Box Class (generic)

    val box: Box<Int> = Box(10) // passed Int Type
    val box2 = Box<String>("String") // passed String
    // we don't even need to mention the type we are passing (<Type>)

    // compiler will infer the type from the argument we are passing..
    val box3  = Box(listOf("this is awesome")) // passed List<String> Type

    // can pass any type that is Inheriting Any Class because of default bound <T: Any?>
    val wrongLiquidContainerOne = WrongLiquidContainer("lol im String not liquid ")

   // can also pass null because default bound is  <T: Any?>(nullable Any Type )
    val wrongLiquidContainerTwo = WrongLiquidContainer(null)

    // but for LiquidContainer generic Class we have specified the bound to <T: Liquid
    // which means we can only pass and store Liquid types and its  SubTypes
    val onlyLiquidContainer =LiquidContainer(Liquid(10)) // Passes Liquid Type object
    val onlyLiquidContainerTwo = LiquidContainer(Water(100)) // passed Water type Object which is SubType of Liquid
    val onlyLiquidContainerThree = LiquidContainer(Patrol(19)) // passed Patrol Type Object which is SubType Of Liquid
    val onlyLiquidContainerFour = LiquidContainer(Paint(10)) // passed Paint Type Object which is subtype of Liquid

    //  try to  pass the Types that are not Bound type Liquid or subtype of it
    // Error: - val otherTypePassTest = LiquidContainer("Im a string and im not type and subtype of Liquid")
    // Error -val nullPassTest = LiquidContainer(null)


}

// Note if we try to bond a class which has no Subclasses of it
open class NoSubClassOnlyMe(val prop: String)
// or
class NoSubClass(val prop: String)

// and you want to bond this  (Type) in a generic class like
class GenericClass<T: NoSubClassOnlyMe> (val prop: T)

// then our generic class serves no purpose and useless and we know we can only pass or store this single Type
// it is as same as declaring simple class like...

class SimpleClass(val noSubClass: NoSubClassOnlyMe )




