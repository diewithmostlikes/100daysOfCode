// okay so iam not done with the classes yet
// there are more topics to explore in classes in kotlin
// but first lets work with pairs types

fun main() {

    // i have used list types or data structure to hold data
    // pair is for same purpose to store data but in its own way


    // lets practice  ......
    // lets say we want to map operating systems to the company the are owned by

    // android is owned by google
    val os : Pair<String , String> = "Android" to "Google"

    // or we can say "Android" is Mapped to "Google

    // getting first element
    println(os.first)

    // getting second element
    println(os.second)

    // can convert it to different types
    println("String representation: ${os.toString()}") // getting string Representation

    // to list
    val listOne: List<String> = os.toList()
    println("List: $listOne")
    // but in this case both elements are of same type which is string
    // and thus toList method of pair is returning list of String (List<String>)

    // lets create a pair whose element type is different
    val pairTest: Pair<String, Int> = Pair("Alex", 20) // using primary constructor of Pair to create Pair Instance or object.
    // now we have a pair instance whose elements are of different type
    // first element is of string type
    // and second element is of Int type

    // lets create a list of this pair
    val listTwo: List<Any> = pairTest.toList()
    // so note if both element of a pair is of same type. Then returned list from toList() method is also of That type
    // but if both the type of both element of pair is different then toList() method will return list of Any type (List<Any>)

    // destructuring declaration
    // by the way Pair is a data class deep down so we can use destructuring declaration
    val (osName, ownerName) = os // note for destructuring we need componentN() functions or methods which kotlin data class provides
    println("$osName is owned by the $ownerName")

    // One more thing like we can create array inside array, list inside list..
    // we can also create pair inside pair

    val pairChainOne = "hello" to "kotlin" to "you are" to "awesome" to "and the best."
    println(pairChainOne.toString())

    // you can add parentheses do things look understandable to you and to pair them in he way you want.
    val pairChainTwo = ("hello" to "kotlin") to ("you are" to "awesome") to "and the best"
    println(pairChainTwo.toString())

    // getting two return values by function with help of pair and destructuring declaration
    val (name, owner) = osDetail()
    println("$name is owned by $owner")

    // That's it from Pair now... :-)

}

// As we know we cant return two values from a function or method. (in python we can return more than one value from function)
// we can use pair and destructuring declaration to return more then one value from method or function
// we will return pair from function or method
// and use destructuring declaration to get the element in inside in different variables

fun osDetail(): Pair<String, String> {
    // well i can do this in one line
    // but written this way to understand more easily by anyone
    val anotherOs = Pair("IOS", "Apple")
    return anotherOs
}