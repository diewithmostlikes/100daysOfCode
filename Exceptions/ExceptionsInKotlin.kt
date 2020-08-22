/** Exception classes in kotlin are descendants of Throwable */
// todo test it -: val storeTest: Nothing = throw Exception("this is a test") // yup we can have reference of the exception and use this reference to throw an exception

// Throwing an Exception
class TestClass // empty class for test
fun throwingException() {
    // can throw an exception by using throw
    throw Exception("Something went Wrong !") // throwing an Exception Class Object and which Extends Throwable

    // lets try  to throw  an object that does'nt Extend or inherit from Throwable Class
    // Error  -: throw TestClass() // yup throw requires an Throwable object to throw
}

// catching an exception
fun tryAndCatch() {
    print("Enter a Number: ")
    val input: String? = readLine() // getting input from user

    // Deal with exception by using try and catch block or expression to handle the exception
    val number : Int?
    try {
        number = input?.toInt()
    }
    catch (e: Exception){
        // this code will execute if something went wrong in the try block
        println("Something went wrong !")
        // or in other words we will execute the code inside the catch block if our specified exception occurs
        // in this case we are using Exception as our exception to catch which will catch Every exception that occurs inside the try block
    }

}

fun tryAndCatchTwo() {
    // lets be more specific with exception that occurs in above example

    println("Enter Your Age: ")
    val input  = readLine()

    val age: Int?
    try {
        age  = input?.toInt()
    }
    catch (e: NumberFormatException) { //  catch block will only catch NumberFormatException that occurs if the string is not valid representation of numbers
        val className = input!!::class.simpleName
        println("Wrong Input Type: $className, You have to enter Integer")
    }

}

// making our custom Exception
// To Do so we need to inherit or extend from Throwable class
// we can use multilevel inheritance to inherit indirectly from Throwable Class
class MyException(msg: String): Exception(msg)

// throwing our custom exception
fun throwCustomException() { throw MyException("This is my Custom Exception") }

// Handling custom Exception with catch blocks
fun handlingCustomException() {
    try {
        throw MyException ("This is my custom Exception") // Manually throwing custom Exception
    }
    catch (e: MyException) {
        println("Voila now we can handle our own custom Exception")
    }
}

// handling multiple Exceptions
class ToLongName() : Exception("Name can be only 20 Character Long")
class WrongNameException(): Exception("Name does'nt Contain Symbols and Numbers")

fun moreCatchBlocks() {
    // we can have more than one catch blocks to handle multiple Exceptions

    println("Enter your name: ")
    val input = readLine()

    if (input != null) {
        val name: String?

        val pattern  = Regex("^[A-Za-z\\s]+\$") // suing regex to check input only contains alphabets and white spaces
        try {
            name = input
            if(!pattern.containsMatchIn(name)) throw WrongNameException() // if name contains symbols and numbers
            if (name.length > 20) throw ToLongName() // if the name is 20 characters long
        }
        // we can many catch blocks to handle multiple exceptions
        catch (longName : ToLongName) { // catch block also takes reference of Throwable class or its derived classes
            println(longName.message) // we can use 'longName' reference to get the Exception message
        }
        catch (wrongName: WrongNameException) {
            println(wrongName.message)
        }
    }

}

fun main() {



}

