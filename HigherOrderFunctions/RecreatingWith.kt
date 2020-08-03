// recreating "With" a standard library lambda or higher order function in different ways

// Every Type of object can be passed.
fun <T> myWith(obj: T, block: T.() -> Unit){ // higher order function
    // Actual object will be modified
    block(obj) // need to pass the object to block()
}

// Recreating "With" so block do'nt have reference to "this"
fun <T> myWithTwo(obj: T, block: (T) -> Unit) { // higher order function
    // Actual object will be modified
    block(obj) // block declaration will not have  reference of "this"
    // so we can't access the properties or method of the instance or object without using "it" argument
    // or without using custom name given to the lambda argument.
}

// Recreating "With" so it can be called directly on objects of every Type
// No need to pass we can call it on Every Type
fun <T> T.myWithThree(block: T.()-> Unit): T { // higher order function
    block() // object will be modified
    return this // returning the modified/unmodified object
}


// todo Recreate "With" that don't modify the Object

fun main() {

    // object will be  modified
    println("================ List One ====================")
    val listOne: MutableList<Int> = mutableListOf(10, 20 , 30, 40, 50, 60)
    println("Before: $listOne")

    myWith(listOne) {// Has reference "this" so we can use properties and methods without specifying  object or lambda argument name
        repeat(3){removeAt(0)} //modifying list
    }

    println("After: $listOne") // modified list


    println("======================ListTwo=========================")
    // object will be modified
    val listTwo: MutableList<Int> = mutableListOf(100, 200, 300, 400, 500, 600)
    println("Before $listTwo")
    myWithTwo(listTwo) {list ->
        // we can't access the properties or method of the instance or object without using "it" argument  or without using custom name given to the lambda argument.
        repeat(3) {
            list.removeAt(0)
        }
    }
    println("After:  $listTwo") // modified list


    // modified object will be returned and modified
    println("================ List Two =====================")
    val listThree: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6)
    println("Before: $listThree")

    listThree.myWithThree{// Has reference "this" so we can use properties and methods without specifying  object or lambda argument name
        //Modifying list
        repeat(3) { removeAt(0)}  // Removing first 3 elements of the list (Modifying)
    }

    println("After: $listThree") // modified list


}
