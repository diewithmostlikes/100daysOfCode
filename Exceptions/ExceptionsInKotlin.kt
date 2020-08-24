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
class ToLongName: Exception("Name can be only 20 Character Long")
class WrongNameException: Exception("Name does'nt Contain Symbols and Numbers")

fun moreCatchBlocks() {
    // we can have more than one catch blocks to handle multiple Exceptions

    println("Enter your name: ")
    val input = readLine()

    val name: String
    if (input != null) {

        val pattern  = Regex("^[A-Za-z\\s]+\$") // using regex to check input only contains alphabets and white spaces
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

// we can also have optional finally block
fun finallyBlock (){
    try {
        throw Exception("Test Exception")
    }
    catch(e : MyException) {
        println("Exception occurred") // runs when a exception defined in catch block occurs

    }
    finally {
        // finally block always executed
        println("hello im finally block ") // runs no matter exception occurs or not
    }
}


// lets use finally block
fun getCorrectAge(): Int{

    println("Enter your age 18 to 60: ")
    val input: String? = readLine()

    var age: Int = -1 // if the age is not correct or an Exception occurs inside the try block bellow then -1 will be returned
    var occurred: Boolean = false
    if (input != null) {
        try {
            input.toInt()
        }
        catch(e: NumberFormatException) {
            occurred = true
            println("Numbers Are allowed only !")
        }
        finally {
            if (!occurred) { // when exception don't occurs means input is in number format and we can check further...
                val ageForCheck: Int  = input.toInt() // this wo2nt give error because we have already checked it in try block
                if(ageForCheck in 18..60) age = ageForCheck // checking age in specific range..
            }
        }
    }

    return  age
}


// can we use more then one finally block ?
fun moreFinallyBlocks() {
    try {
        throw Exception("Test Exception !")
    }
    catch(e : Exception){
        println("handling....")
    }
    finally { // even one final block is also optional.
        println("finally block 1")
    }
    // Error: finally { println("finally block 1") }
    // so nope ! we can't have more then one finally block and it serves no purpose to have more finally blocks

}

// try is an expression so it may have return value
// lets try to return some values from try expression
fun returningFromTry() {
    val input: String = readLine() ?: return // if the input is null we will end the function

    // return value from try exception is either last expression of try block or catch block
    val getValue: Int  = try { input.toInt() } catch (e : Exception) { -1 }

    // what happens here is ->
    // if there no exception occurs in code inside the try block then last expression of try block will be returned as return value
    // if some Exception occurs or thrown by the code inside the tyr block then last Expression inside catch block will be returned as return value

}

 // what if we try to return from finally also.
fun returningFromFinally() {
    val input = readLine() ?: return

    val getValue: Int = try {input.toInt()}  catch(e :NumberFormatException) { -1} finally { 0 }
    // finally block does not effect the return value and in the above example last expression inside final block gets unused

}

// lets use try expression return value in the above age example
fun getCorrectAgeTwo(): Int {
    val input = readLine() ?: return -1
    var age: Int = -1

    val occurred: Boolean  = try {
        input.toInt()
        false
    }
    catch(e: NumberFormatException) {
        println("Only numbers are allowed ")
        true
    }

    if (!occurred){
        val ageForCheck = input.toInt()
        if (ageForCheck in 18..60) age = ageForCheck
    }

    return age
}



fun main() {




}

