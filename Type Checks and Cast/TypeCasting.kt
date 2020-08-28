
/**Type Casting */
// we can cast a Type to another type (if it confirms to given Type )
// There are two type of Explicit casting


/** unSafe Casting */
fun unsafeCasting(obj: Any?){
    // Unsafe Casting is done using infix operator "as"
    // This is called Unsafe Casting because if the casting is not possible "as" an throw Exception (ClassCastException)

     val x: String = obj as String
    // if the obj is null it Throws an Exception (TypeCastException)


}

fun unsafeCastingNullObject(obj: Any?) {
    // but if we do this
    val y: String? =  obj as String?
    // then if the obj confirms to the Type String compiler will cast it to String (String?) means we can cast null to String?
}

/** Safe (nullable) Casting */

fun safeCasting(obj: Any?) {

    // it is called safe casting
    // because if any Exception occurs in Casting it will return null
    // thus we have used nullable String (string?) as type of z
    val z : String? = obj as? String

    if (z != null) println("Cast successful !")
    else println("Cast unsuccessful :-(")
}

fun main() {

    // unsafe Casting instance of Any
    val anyObj  = Any()
    // ClassCastException -> unsafeCasting(anyObj)

    // unsafe casting null
    // TypeCastException -> unsafeCasting(null)
    unsafeCastingNullObject(null) // No Exception


    // safe casting instance of Any
    safeCasting(Any()) // unsuccessful !
    safeCasting("im String") // successful :-)

    // Safe Casting null
    safeCasting(null) // unsuccessful :-(

}
