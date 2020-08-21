/** Exception classes in kotlin are descendants of Throwable */
val storeTest: Nothing = throw Exception("this is a test") // yup we can have reference of the exception and use this reference to throw an exception

fun throwingException(){
    throw Exception("Something went Wrong !") // throwing an exception
}

fun tryAndCatch(){
    // to deal with exception we can use try and catch block or expression
    print("Enter a Number: ")
    val input: String? = readLine()
    val age: Int

}

// todo topic will be covered tomorrow


