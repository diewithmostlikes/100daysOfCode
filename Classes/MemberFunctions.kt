// previously we saw some basics about classes and talked a little about
// member functions and thus we are going to cover them here

// creating a alien class
class Alien(val power: Int, val intelligenceLevel: Int )

// creating Aliuman class
class Aliuman(human: Person, alien: Alien) {
    val name = "ali-" + human.name
    val ultimatePower = alien.power + human.strength
    val intelligence: Int  = alien.intelligenceLevel + human.iq

    fun fly() {
        println("flying......")
    }


    // string representation of Aliuman
    override fun toString(): String {
        return """
            I am ${this.name},
            I am the fusion of human and alien .
        """.trimMargin()
    }
}

// creating Person Class
class Person(val name: String, val age: Int) { // primary constructor

    val iq: Int  = 90 // 90 for example.
    val strength: Int
        get() { // getting strength percentage based on age
            return when {
                age in 1..16 -> 20
                age in 17..35 -> 95
                age > 35 -> 60
                else -> 20
            }

        }


    val friendList: MutableList<Person> = mutableListOf() // created a mutable list
    val alienFriendList: MutableList<Alien> = mutableListOf()

    // simple member function
    fun walk(fast: Boolean = false){ // Used default parameter
        println("Hey im walking !!")
    }

    /** creating infix functions
     * infix functions are functions that accepts only single agrument
     * or we can say they have single parameter declared
     * To declare a infix function we use keyword "infix" before "fun" keyword
    **/

    // infix function declaration
    //  remember infix will only work with single parameter function or method
    infix fun talkTo (person: Person) {
        println("Hello, I am ${this.name}. How are you ${person.name}")
    }

    // now we will practice  about method overloading
    // when we need to pass different number or Type of parameter to a same function or function that do same task

    // eg

    // function to person to friendList
    fun addFriend(person: Person) {friendList.add(person)} // function with single parameter


    // here this function can only add one friend
    // but what iff we want to add lets say 2 or more friend to friend list
    // we can define other function to do that like addTwoFriends() but... here we have to create a lot more functions with different names
    // there is a better way to do that called method overloading


    //eg.

    fun addFriend(person1: Person, person2:Person) { // same function or method name but different no of arguments( function with two parameter)        // now we can add two friends to friendList
        friendList.add(person1)
        friendList.add(person2)

    }
    // we can do this any number of time. just the same name but different number of parameters
    // but those are our human friends lets say if we want to add alien friends and human friend to our alien and human friend list

    // we can again overload addFriend method to do so

    fun addFriend(person: Person, alien: Alien){
        // same function with two arguments but this time with different types
        friendList.add(person)
        alienFriendList.add(alien)
    }

    fun addFriend(alien: Alien) { alienFriendList.add(alien) } // addFriend with single parameter but different type

    // so as it is clear now instead of creating different name functions that do similar or same task
    // we can declare a function with same name but change the number of parameters or type of the parameter

    // but what if want to add 10 or 20 friends
    // it would take a lot of time to declare those 20 parameters
    // to overcome that here we can declare single parameter using varargs keyword

    //eg
    fun addFriend(vararg personArray: Person) {  // vararg takes all the argument passed and create an array of it.
        for (person in personArray) friendList.add(person)
    }

    // same as above but different type
    // we also call it variadic function and parameter is called variadic parameter
    fun addFriend(vararg alienArray: Alien) { // vararg takes all the argument passed and create an array of it.
        for (alien in alienArray) alienFriendList.add(alien)
    }

    // woof.. saves a lot of time..


    /** it would be great if a person can have fusion with aliens
     * then we can use their powers and intelligence
     * hey wait a sec this is programing we can do anything we want to create (ha ha ha ha i'm a mad scientist....)
    */

    // for doing so we can create new function or...
    // we can define '+' operator function  so that it can work  when we use this operator with alien instances
    // eg.


    // when we write  <instance of Person> +  <instance of Alien> its going to return us instance of  Aliuman
    operator fun plus(alien: Alien): Aliuman = Aliuman(this, alien)

    // we can also overload operators this is called operator overloading
    // same number of arguments but different types here
    operator fun plus(number: Int): String = this.name + "10"

}

fun main(){
    // Use of infix function

    // creating  objects of Person
    val john: Person = Person("John", 22)
    val alexa: Person = Person("Alexa", 22)
    val sam: Person = Person("Sam", 40)
    val  david: Person = Person("David", 25)
    val luther: Person = Person("Luther", 14)

    // creating objects of aliens
    val alien1: Alien = Alien(100, 100)
    val alien2: Alien = Alien(80, 100)
    val alien3: Alien = Alien(50, 40)
    val alien4: Alien = Alien(88, 20)
    val alien5: Alien  = Alien(10, 100)

    // lets call john's talkTo function and pass alexa in it
    john.talkTo(alexa)

    // it works same as the simple function so whats the purpose of the infix keyword before the fun
    // check this example now

    john talkTo alexa    // we can totally omit the '.' (dot) and '()' (parentheses)
                        // Here talkTo work like a keyword to us or we call it infix operator.

    // That's the use of the infix function
    // Note infix only works with single parameter function or method

    // lets add some friends to john friend list
    john.addFriend(alexa) // alexa is added  now to his friend list
    john.addFriend(alien1) // added an alien to john alien friend list

    // lets add three human or person and one alien to alexa friend list and alien friend list Respectively
    alexa.addFriend(david, luther)
    alexa.addFriend(sam, alien5)

    // lets add all the aliens and all the human or person friends to the friendList and alienFriendList of david
    david.addFriend(alien1, alien2, alien3, alien4, alien5) // added all the alien to david's alienFriendList
    david.addFriend(luther, sam, john, alexa) // added all the human or person to david friendList

    //or we can create array of all person or alien and then pass it to addFriend method or function
    val personList: Array<Person> = listOf(john, sam, david, alexa).toTypedArray()
    luther.addFriend(*personList) // make sure u pass it by adding '*' in front of the array like i did so.



    //  now lets become a mad scientist and fuse alexa to alien1 ah ah ha ha ha ha ha....
    val aliuman: Aliuman =  alexa + alien1

    // lets print out what we have made
    println(aliuman.toString())


}












