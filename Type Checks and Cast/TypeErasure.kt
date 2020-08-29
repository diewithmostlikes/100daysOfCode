/** right now im testing and learning this up so its going to be mess here :-) */

// lets perform is checks for generic type (in this case List)
fun aboutValue(list: List<Any>) {
    // At runtime instances of generic type holds no information about their actual type argument
    // here List<Any> is erased to List<*>

    //Error -> if(list  is List<String> ) println("List of String")
    // Error -> if(list is List<Int>) println("List of Int")

    // In general there is no way to check weather an instance of (belongs to) generic type with certain type argument at runtime

}

fun main(){

}