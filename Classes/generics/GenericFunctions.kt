package generics

// Just like generics classes we can also make function or methods generic.
fun <T> printAnyType(type: T){ // use <T> generic constraint to declare the function generic
    println(type) // printing string representation
}
fun main() {
    printAnyType("this is us ")

}
