// non inlined function
fun higherOrderFunc(lambda: () -> Unit) {lambda()}

// inlined function
inline fun inlineHigherOrderFunc(lambda: () -> Unit) {lambda()}

// we cannot use return keyword inside higher order function's lambda expression.
fun simpleFuncOne() {
    higherOrderFunc{
        println("This is a lambda expression of higher order function (without inline )")
        // Error -return
    }
    println("Im simpleFuncOne !")
}

fun simpleFuncTwo() {
    inlineHigherOrderFunc{
        println("This is lambda expression of inlined higher order function")
        // allowed in inlined functions
        return // non local return
        // means Return is present in the lambda expression and it actually returns or exits the enclosing function (simpleFuncTwo)
    }
    println("Im simpleFuncTwo") // won't get to this point
}

// we can use labels to avoid non local returns
fun simpleFuncThree(){
    inlineHigherOrderFunc here@{
        println("using label to avoid non local return")
        return@here
    }
    println("This will get printed")
}

fun main(){
    simpleFuncOne()
    simpleFuncTwo()
    simpleFuncThree()

    higherOrderFunc {
        println("Higher order function is not inline and thus cant use return")
        //Error -: return
    }

    inlineHigherOrderFunc{
        println("Higher order function is inline and thus can use return ")
        return // return allowed !
    }

    println("this will not get printed") // because the inlineHigherOrderFunc lambda expression above has non local return
                                         // which will return or exit the main() function


}


