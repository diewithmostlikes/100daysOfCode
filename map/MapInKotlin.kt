// so we are done with pair and triple
// lets practice map


fun main() {

    // declaring and initialising map
    // So Map is key and value type data structure

    val userInfo: Map<String, Int> = mapOf("Alex" to 1201, "john" to 1202) // Note mapOf takes pair or array of Pair (varargs)

    // first element of the pairs becomes the keys
    println(userInfo.keys) // keys property to get set(type) of keys

    // And second element of pairs becomes the value
    println(userInfo.values) // value property to get Collection(type) of values
    border() // To print border

    // we can access the value by using key
    val alexId: Int?  = userInfo["Alex"]  // key found and it returns the value.
    val davidId: Int? = userInfo["David"] // Note if the key is not found it will return you null
    // Note we can use getValue()  and pass the key as argument to it and it will throw NoSuchElementException if the key is not found in the map
    println("Alex id : $alexId\nDavid id : $davidId")
    border() // print border

    // The Map keys are unique and map hold one value for each key

    val mapWithDuplicateKeys: Map<Int, String > = mapOf(1 to "alex", 2 to "john", 1 to "david", 3 to "sam")

    // now i have two same keys in above map 1 for "alex" and also 1 for "david"
    // lets try to access the value
    // i don't know it should give me error or throw me a exception something like duplicate keys but its not
    val duplicateKeyTest: String? = mapWithDuplicateKeys[1]
    println(duplicateKeyTest)

    // So if we have defined a key more then one to different values
    // and we try to access the value of that key
    // its going to return us the value of the last Passed pair with duplicate key to the map
    // in this case last passed pair with duplicate key value is david

    // Reassigning the values to the key
    // To doing so we have to create mutable map
    val userInfoMutable: MutableMap<String, Int> = userInfo.toMutableMap()
    // lets change Alex Id
    println("Alex Id Before : ${userInfoMutable["Alex"]}")
    userInfoMutable["Alex"] = 10 // changing alex id to 10
    println(("Alex id After Changing: ${userInfoMutable["Alex"]}"))
    border() // printing border

    // what if the key does'nt exists and we try to change the value
    userInfoMutable["David"] = 1203
    println(userInfoMutable.toString())
    // if we try to change the value of the key which does'nt exists in map then it will add new key value pair to the map.
    // wow nice but it would be better if we try to access the value from the key which does'nt exists and it automatically add to the map

    // now  the "Sam" (key) does'nt exists
    // And userInfoMutable["Sam"] getter method will going to return null
    // and thus elvis operator (:?) will return the expression (Int)  on the right side of it.
    userInfoMutable["Sam"] = userInfoMutable["Sam"] ?: 1204
    println(userInfoMutable.toString())
    border()

    // but we actually have the method to do so
    // getOrPut last parameter is requires a function that must return instance of the type of value type  of map
    // and as we can pass lambda we can put it outside the parenthesis
    val kotlinId: Int =  userInfoMutable.getOrPut("Kotlin") { 1 } // adds  pair to the  map if the key doesn't exists.
    println("Kotlin Id: $kotlinId")
    println(userInfoMutable.toString())
    border()

    // if we don'nt want to add a new pair to the map in case we don't have the key present in the map
    // and just want to get a default value without adding a new pair to the map if the key is not present in the map
    // we can use getOrDefault() method of map to do so
    val googleId: Int = userInfoMutable.getOrDefault("Google", 100)   // does'nt add the new pair to the map.
    // or we can also use getOrElse() 
    // it Requires the key and function or lambda as argument
    // we can use this method to first calculate or tweak something before returning the value because it provides the lambda argument to pass
    val jetBrainsId: Int = userInfoMutable.getOrElse("Jet Brains") {kotlinId + 9}   // it will also not add new pair to map
    println("Google Id : $googleId")
    println("Jet Brains Id : $jetBrainsId")
    println(userInfoMutable.toString())
    border() // printing border


    // lets see what happen when we add two maps
    val map1: Map<Int, String> = mapOf(1 to "kotlin")
    val map2: Map<Int, String> = mapOf(2 to "Python")
    val map3: Map<Int, String> = map1 + map2   // plus operator returns a new map that has both map's key and values
    println(map3.toString())
    border()
    
    // what if we add a pair to the map
    val map4: Map<Int, String> = map3 + (3 to "tenser flow")    // returns new map with added key value pair
    println(map4.toString())
    border()

    // what if the type of both operands don't match
    val map5 : Map<String, Int> = mapOf("Android" to 4)
    val map6: Map<Any,Any> = map4 + map5  // adding maps that have different type will return map of Map<Any, Any>
    println(map6.toString())
    border()

    // minus creates a map of the keys and values which is present in left side map operand except the keys that are specified on the right side and their values too.
    // for eg
    val map9: Map<Any, String> = map4 - 2  // 2 is key here
    println(map9.toString())
    border()

    // right hand operand can also be of type array, Sequence and the types which implements iterable interface which have keys
    // for example  List

   val emptyMap: Map<Int, String> = map4 - listOf(1, 2, 3)
    println(emptyMap)
    border()

    // Inserting in Map
    val studentInfo: MutableMap<Int, String> = mutableMapOf(1 to "Alex")
    // we can add pair by using put() method or use shorthand "[]"
    studentInfo.put(2, "David")

    // by using shorthand "[]"
    studentInfo[3] = "Sam"

    // but  be care full if there is the key already present in the map you are gonna reassign the value of that key.
    // just make sure the key is not present there already.
    println(studentInfo.toString())
    border()

    // we can also add group of pairs by using put all
    val pairList: List<Pair<Int, String>> = listOf(4 to "john", 5 to "koltin", 6 to "google", 7 to "john") // well i run out of the names thus used these here
    studentInfo.putAll(pairList)
    println(studentInfo.toString())
    border()

    // and we can use plus assign operator
    studentInfo += 8 to "jet Brains"
    println(studentInfo.toString())
    border()

    // and last but not least deletion  or removing entries from map

    // we can use remove() method
    studentInfo.remove(1) // we can remove key value pair by using a specific key
    println(studentInfo.toString())
    border()

    studentInfo. remove(2, "David") // we can remove a particular pair. (if one of them key or value doesn't match its not going to remove anything)
    println(studentInfo.toString())
    border()

    // we can remove pair from map by using keys and values remove Method
    studentInfo.keys.remove(3)
    studentInfo.values.remove("john") // will only remove the value which first occurs or removes the first entry with the given value.
    println(studentInfo.toString())
    border()

    // and we can also use minus assign operator
    studentInfo -= 6 // 6 is the key here to get removed
    studentInfo -= 7
    studentInfo -= 10 //removes nothing because there is no matching key
    println(studentInfo)
    border()

    // so im back lets practice using filter() function on map
    // so filter on maps takes predicate with the pair argument making it possible to filter with key and value both

    val music = mapOf("Old Town Road" to "Lil Nas X", "Bailando" to "Erique", "Titanium" to "David Guetta" )
    val filteredMap = music.filter{ (key, value) -> key.startsWith('O') && value.endsWith('X') } // returns a new filtered Map
    println(filteredMap.toString())
    border()

    // we cn also filter map only using keys or only using values by using filterKeys() and filterValues() functions
    val filteredMapWithOnlyKeys = music.filterKeys{ it.startsWith('T')}
    println(filteredMapWithOnlyKeys)
    border()

    val filteredMapWithOnlyValues = music.filterValues{it.startsWith('E')}
    println(filteredMapWithOnlyValues)
    border()

}

fun border() {
    repeat(50){ print("=") }
    println()
}