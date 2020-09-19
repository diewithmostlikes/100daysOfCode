/**Bounded functions and property reference*/

class LightBulb(val color: String, val watt: Int) {
    private var isOn = false

    fun turnOn() {
        if (!isOn) {
            isOn = true
            println("$color light is glowing everywhere !")
        }
        else println("Already on !")
    }

    fun turnOf() {
        if (isOn){
            isOn = false
            println("its dark here !")
        }
        else println("Already off !")
    }

    inner class Test  {
        val prop = "for illustration"
    }
}

fun boundedRef() {
    // create our light bulb
    val bulb = LightBulb("yellow", 100)

    // ref to instance method of a object
    val turnOn: () -> Unit  = bulb::turnOn // has receiver attached soo receiver is no logger parameter.
    // changes will be made on the object whose instance method it is
    turnOn() // has receiver attached to it so we can use it directly without passing the object

    // unbound reference
    val turnOf: (LightBulb)  -> Unit = LightBulb::turnOf // Receiver is not attached so receiver is parameter
    turnOf(bulb) // need to pass receiver object


    // same for the properties
    val colorBoundedRef = bulb::color
    val colorUnBoundedRef = LightBulb::color

    println(colorBoundedRef.get())
    println(colorUnBoundedRef.get(bulb)) // need to pass receiver object

    // bound constructor ref

    // from unbounded reference.
    val innerClassConstructor: (LightBulb) -> LightBulb.Test =  LightBulb::Test
    val obj = innerClassConstructor(bulb) // need to pass receiver because receiver is not attached

    // from bounded reference
    val innerClassConsTwo = bulb::Test
    val objTwo = innerClassConsTwo() // receiver is already attached

}

fun main () {
    boundedRef()
}

