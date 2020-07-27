import java.util.*

// so we have seen classes that needs only single instances to be created and many instance of those class seems unnecessary
// because all they do same things or their purpose will be same no matter how many instance we create of those type of class

// for rg

class RedColor { val color: String = "Red" }

// so this class have a property color red and no matter how many instance of this class we are going to create
// is going to do the same thing.

//  for creating this type of classes kotlin provides a Singleton class and we  can only have or create exactly single Instance of singleton class
// singleton class is declared by "object" keyword

// eg

object Red {
    val color: String = "Red"
}

// now it makes more sense to have only single instance of the Red Class..
// Note constructor are not allowed in singleton class because if you want to have a constructor that means your class
// take different values and every instance is going to be different. it means you should probably consider using simple class

// Now we know we can use simple class declared with "class" to have multiple instances
// or we can use singleton class declared with "object" to exactly have one instance of the class (nothing more nothing less)

/** but there is a concept called enum classes
 * what and why they are used for ?
 * so sometimes we need to have a particular number of instances we want to create of a class.
 * And thats where enum classes come in.
 */

// like we just want 7 main color instances
// we can do this two way create singleton classes for every 7 color
// or we can use enums
// okay enough talk lets code....

enum class Color(val hex: Int){
    RED(0xff0000), // this is called enum constant and it is an object or instance. And separated by commas
    YELLOW(0xffff00),
    GREEN(0x00FF00), // notes these are instance so they can also have properties and methods
    ORANGE(0xff6600);
    // enum class can also have companion objects


    // instances can also have methods.
    fun about(){
        println("Im $this and im  from Color enum class ")
    }

    fun getHexValue() = this.hex


    // Things that Does'nt depend on the instance of the class or remains same for the instances of the class
    companion object {
        fun randomColor(): Color {
            return when((1..4).random()) {
                1 -> RED
                2 -> GREEN
                3 -> ORANGE
                else -> YELLOW
            }
        }
    }

}

// Enum Constants can have their anonymous classes
enum class EnumClass{
    CONSTANTA{ // Anonymous Class
        override fun aMethod(){
            println(this.name)
        }
    },
    CONSTANTB{ // Anonymous Class
        override fun aMethod(){
            println(this.name)
        }
    },
    CONSTANTC{ // Anonymous Class
        override fun aMethod() {
            println(this.name)
        }
    };


    // I have marked this function or method as abstract and thus i have to implement it in every instance or constant of enum
    abstract fun  aMethod()

}



// enum class can also implement interface
// creating interface
interface WeekEnd {
    fun isWeekEnd(): Boolean
}

// creating enum class
enum class Days: WeekEnd{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY{
        override fun isWeekEnd(): Boolean = true // implementation for only SATURDAY
    },
    SUNDAY{
        override fun isWeekEnd():Boolean = true // implementation for only SUNDAY
    };

    // implementation for all instances of enum class
    override  fun isWeekEnd(): Boolean = false

}






fun main() {

    // now we have created our enum class with some instances or constants
    // lets access them and their property


    // enum classes in kotlin have spacial features lets use them
    println("I love ${Color.RED.name}") // Note that we have not defined name property in our enum class Kotlin provides this.

    // don't have to use .name because if we try to print the object its going to print the name of instance.
    println("I love ${Color.RED}")

    // we can get array of declared instances by using .values() method
    val colorArray: Array<Color> = Color.values()
    println(colorArray.toList())

    // we can also search for  a instance in enum class
    val greenColor: Color  = Color.valueOf("GREEN")
    println(greenColor.name)

    // what if we search for the instance that does'nt exists
    //ERROR -: val blue: Color = Color.valueOf("BLUE")
    // Compiler will throw you an IllegalArgumentException if the instance that you searched for doesn't exits

    // lets use class method randomColor
    val randomColor: Color = Color.randomColor()
    println("And the color is........ $randomColor")

   // we can access the properties of the instances
    val yellowHex: Int = Color.YELLOW.hex
    println("Hex value of yellow: $yellowHex")

   // Accessing about() method of the instance of Color enum class
    Color.ORANGE.about()
    // Error Color.about() because the about is method of every instance or constant of the enum class not the class method


    // Now lets check our enum class Day that is implementing WeekEnd Interface

    // Accessing isWeekEnd() method of the instances of enum class
    println("""
        Sunday: ${if(Days.SUNDAY.isWeekEnd()) "Holiday" else "Not Holiday "}
        Saturday: ${if (Days.SATURDAY.isWeekEnd()) "Holiday" else "Not Holiday "}
        Monday: ${if (Days.MONDAY.isWeekEnd()) "Holiday" else "Not Holiday "}
    """.trimIndent())

    // one more thing in enums.. like Arrays they have index that start from zero
    // We have ordinals in enums which represent the order of the  instances  in enum class

    //for example

    // printing all the day with ordinals
    // Note ordinal also starts from zero
    for (day in Days.values()) println("Day ${day.ordinal}: $day")


}

fun isWeekEnd(day: Days): Boolean {

    // using enum with when..
    return when (day) {
        Days.MONDAY -> false
        Days.TUESDAY -> false
        Days.WEDNESDAY -> false
        Days.THURSDAY -> false
        Days.FRIDAY -> false
        Days.SATURDAY -> true
        Days.SUNDAY -> true

        // note compiler is not complaining about else branch though we are using when as expression
        // because we have used all the possible cases and when knows that....
    }


}








