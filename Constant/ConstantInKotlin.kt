const val valueAtCompileTime = "initialising constant" // constants are initialised at compile time.
// for constant value is always determined at compile time
// on the other hand val can be assigned at runtime. or there value can be determined during the execution

/** the difference btw const and val is
 * val can be initialised at runtime
 * but constants has to be set at compile time*/


/**Error const val aConstant  = getConstantValue() */
// we cant get value from function because functions are evaluated at runtime i guess and constants are set on compile time
// and thus we cant call and execute the function to get constant value


// only works with top level and classes declared with object not with the regular class.
object RedColor {
    const val RED = "Red"
}

class ConstantTest {
    // error : val test = 10 constants are not allowed inside a class const

    // and can also work with companion object
    companion object {
        const val sameValue = 10 // we can use it in companion object
    }
}
fun main() {
    // Error : const val aConstant = 10 // also not work in functions and methods
}

// one liner function
fun getConstantValue()  = "constant value"