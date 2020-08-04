// how lambda works inside the higher order function

// Declaring simple higher order functions
fun higherOrderFunction(operation: () -> Unit ){ operation() } // invoke method is called (operation.invoke())

// declaring inline higher order function
inline fun inlineHigherOrderFunction(operation: () -> Unit) { operation() }

// This is how lambda expression or lambda  object is created
object MyLambdaObject: Function0<Unit> {
    override fun invoke() {
        println("this is my lambda object")
    }
}

fun main(){

    // simple representation of higher order function
    higherOrderFunction{ // every time we call higherOrderFunction it will create a new lambda object which takes CPU time and Memory means overhead.
        println("This is a Higher order Function")
    }

    // behind the seen without inline
    higherOrderFunction(object: Function0<Unit>{ // lambda expression is an instance of function interface
        override fun invoke(){
            println("this is how lambda expression looks..")
        }
    })

    // Simple Representation of inline higher order function
    inlineHigherOrderFunction{ // call to the lambda is replaced with the content of the lambda expression
        val name = "kotlin"
        val upperName = name.toUpperCase()
        println(upperName)
    }

    // behind the seen with inline (ofCourse in java)
    val name: String = "kotlin"
    val upperName = name.toUpperCase()
    println(upperName)

    // lambda is object which is instance of Function Interface
    MyLambdaObject() // invoke function works when parenthesis are used )

}


