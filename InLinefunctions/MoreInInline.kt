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
        // Return is allowed in inlined functions
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

// Non local control flow
 inline fun inlineHigherOrderFuncTwo( operation: () -> Unit) {
   /* val runnable = object: Runnable{
        override fun run() =  operation() // Error because we are not accessing passed lambda directly in function body but from
                                          // another execution context which is called non local control flow
                                          // and its not allowed in lambdas
    }
    */
}

// To allow this type of Non local control flow we can use the crossinline modifier
inline fun inlineHigherOrderFuncThree(crossinline operation: () -> Unit) {
    val runnable = object: Runnable{ // yeah i know i can do this with lambda but just to illustrate better
        override fun run() = operation()
    }
    runnable.run()
}

fun simpleFuncFour(){
   inlineHigherOrderFuncThree{
       println("im running")
       //Error -: return also we cant use return in lambda that has crossinline modifier.
   }
}

// sometimes we want some lambda in higher order function to not to be inline but we still want other lambda parameter to be inline as well as inline higher order function
// well for that case we can use noinline modifier
inline fun inlineHigherOrderFuncFour(lambdaOne: () -> Unit, noinline lambdaTwo: () -> Unit) {
    lambdaOne() // inline lambda
    lambdaTwo() // not inlined lambda
}

fun simpleFuncFive() {
    inlineHigherOrderFuncFour(
            { // passing lambdaOne
                println("this is inline lambda ")
                return // allowed because lambda is inlined
            },

            {// passing lambdaTwo
                println("Not inlined lambda") // wont reach here because above lambda will do non local return.
                // return -: Error because lambda is inlined
            }
    )
}

fun main(){
    simpleFuncOne()
    simpleFuncTwo()
    simpleFuncThree()
    simpleFuncFour()
    simpleFuncFive()

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


