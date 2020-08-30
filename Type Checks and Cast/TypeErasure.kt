/** right now im testing and learning this up so its going to be mess here :-) */

// lets perform is checks for generic type (in this case List)
fun <T> aboutValue(list: List<T>) {
    // At runtime instances of generic type holds no information about their actual type argument
    // here List<Any> is erased to List<*>

    //Error -> if(list  is List<String> ) println("List of String")
    // Error -> if(list is List<Int>) println("List of Int")e
    //Error ->  if (list is T) println("curiosity huh.. ??")

    // In general there is no way to check weather an instance of (belongs to) generic type with certain type argument at runtime

    // that's why compiler prohibits is checks at runtime due to type erasure
    // like the examples above
}

// how ever we can check instance against Star Projected types
fun <T> starProjectInstanceCheck(list: List<T>) {

    if (list is List<*>){
        // items are typed as Any?

        // so lets print them with class name
        list.forEach{ println(it) } // note here items are types as Any?

    }

}

// but what if we have typed argument of  generic type
fun haveTypedArgument(list: List<Int>) {

    if (list is ArrayList<Int>){ // because ArrayList implements List interface well indirectly...
        // as we know in this function we can pass List<String> or its Subtypes like ArrayList<String>
        //  so we can make is checks and have smart casting

        // list is  smart casted to ArrayList<String>
        println(list::class.simpleName)
    }

    // as the type Argument remains the same like here Which is String
    // we can omit angle brackets
    if(list is ArrayList) println("yee.. no angle brackets !")

    println(list::class.simpleName)
}



fun main(){

    val numberList  = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val numberArrayList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    haveTypedArgument(numberList) // passing List
    haveTypedArgument(numberArrayList) // passing subtype of list here in this case ArrayList
}