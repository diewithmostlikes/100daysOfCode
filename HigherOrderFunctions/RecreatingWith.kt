// recreating "With" a standard library lambda or higher order function in different ways

// Every Type of object can be passed.
fun <T> myWith(obj: T, block: T.() -> Unit){
    // Actual object will be modified
    block(obj) // need to pass the object to block()
}

// todo Recreating "With" so the actual object should not be modified


// Recreating "With" so it can be called directly on objects of every Type
// No need to pass we can call it on Every Type
fun <T> T.myWithTwo(block: T.()-> Unit): T { // using generic extension lambda function
    block() // object will be modified
    return this // returning the modified/unmodified object
}


fun main() {

    // object will be  modified
    println("================ List One ====================")
    val listOne: MutableList<Int> = mutableListOf(10, 20 , 30, 40, 50, 60)
    println("Before: $listOne")

    myWith(listOne) {
        repeat(3){removeAt(0)} //modifying list
    }

    println("After: $listOne") // modified list


    // modified object will be returned and modified
    println("================ List Two =====================")
    val listTwo: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6)
    println("Before: $listTwo")

    listTwo.myWithTwo{
        //Modifying list
        repeat(3) { removeAt(0)}  // Removing first 3 elements of the list (Modifying)
    }

    println("After: $listTwo") // modified list


}
