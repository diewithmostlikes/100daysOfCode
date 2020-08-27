import kotlin.math.sqrt

/** is and !is */
// we can check weather an object conforms to a given type at runtime by using 'is' operator (negated form !is)

fun isAndIsNot(obj: Any ) {

    if (obj is String) { // if obj confirms to be of string type
        println("obj is of String type")
        println("length of name is ${obj.length}") // then we can perform string based operations on obj
    }

    if (obj !is String) println("obj is not of String Type ")
}

/** Smart Cast  */
// In many cases we don't need to use cast operator in kotlin
// Because compiler tracks "is checks" and "explicit cast" for immutable values
// And insert (safe) cast automatically when needed

fun smartCast(obj :Any){
    if (obj is Double) {
        val sqrt  = sqrt(obj) // obj is automatically cast to Double
        println("Sqrt is : $sqrt")
    }
}

fun smartCompilerForSmartCast(obj :Any) {
    if (obj !is String) return

    println(obj.length) // compiler knows if the above check leads to return means this line wont execute because control will not reach this point
    // but if above check doesn't lead to return means the obj is of given type and it automatically(smart cast)  obj to given type

}

// Smart cast in right hand side of ||
fun smartCompilerForSmartCastTwo(obj: Any){
    if (obj !is String ||  obj.length == 0 ) return

}
// Smart cast right hand side of &&
fun <T> smartCompilerSmartCastThree(obj: Any) {
    if (obj is String && obj.length > 0) {
        println(obj.length)
    }
}

// smart cast for when
fun smartCastWithWhen(obj: Any) {

    // smart cast works for when statement also
    when(obj) {
        is String -> println("Length: ${obj.length}") // obj is automatically cast to String
        is Double  -> println("Sqrt: ${sqrt(obj)}")
        is Char -> println("obj is of character type !")
    }

}

fun smartCastWithWhile(obj: Any) { } // todo

fun main() {

    // passing instance of Any
    val testObj  = Any()
    isAndIsNot(testObj)

    // passing instance of string
    val testString  = "Im a String.."
    isAndIsNot(testString)

    // Smart cast
    val double = 16.0
    smartCast(double)
    smartCompilerForSmartCast("test")

    // Smart Cast with when
    smartCastWithWhen('k') // passing Char
    smartCastWithWhen("Koltlin") // passing String
    smartCastWithWhen(32.0) // passing Double

}