import kotlin.properties.Delegates

class Food //  Empty Class

class Fish(val friendly: Boolean, volumeNeeded: Int ) { // primary constructor

    // properties must be initialised
    val speed: Int  = 5 // lets take 5 cm/s for example.
    /**
     * kotlin Compiler gives you getter and setter method by default for the properties you don't have to declare them by yourself..
     * Note properties declared with val only have get() method (getter)
     * And properties declared with var have both get() and set() method (getter and setter)
    **/

    // Error -: var speed: Int (error if we don't initialise the properties)
    // we can only do so if we are initialing the property in init block or overriding the get() method

    // example -:
    var propOne: Int  // no error here because we have initialised the property init block
    init {
        propOne = 10
    }

    // or

    val propTwo: Int // no error because we have overridden the default get() method for properties
        get() = 10  // Note  This works only for the property initialised with the val


    /**
     * And lastly we can use " Lateinit var " to declare properties
     * that we are going to initialise anytime we want..
     * But in most cases we use it to initialise the property on runtime
     */

    // Example
    lateinit var propFour: Food // we can initialise this property anytime we want.

    /**
     * Note that if you access the properties declared with lateinit
     * before initialising... it will Throw you  an exception uninitializedPropertyAccessException.
     **/

    // Also note lateinit var does'nt work with primitive Types
    // for eg.. lateinit var testProp: Int (the type is Int which is primitive type) this will not work with lateinit var
    // wondering why this happens check out this Stackoverflow Question
    //https://stackoverflow.com/questions/38761294/why-doesnt-kotlin-allow-to-use-lateinit-with-primitive-types

    // For Primitive properties we can use
    val propFive by Delegates.notNull<Int>()
    // it will throw an IllegalStateException if you access it before initialising it.



    // secondary constructors
    // it must call the primary constructor
    constructor(volumeNeeded: Int): this(true, volumeNeeded) { // In most of the cases we don't need the secondary constructor
        println("secondary constructor working....")
    }

    constructor() : this(10) { // Calling secondary constructor above who then calls the primary constructor

    }


    val size: Int
    // init block
    // init block works or executes on object creation
    init {
        size = if (friendly) volumeNeeded * 2 else volumeNeeded // size of fish based on the friendliness
        println("First init block working.....")
    }

    // we can have any number of init block anywhere in our class
    // they execute in  the order of they are written (Top to Bottom of class)
    // init block  run before any secondary constructors
    init {
        println("Second init block working  ")
    }
    
    // Note if we are using any property inside init block then property need to be come before the init block
    // Other wise editor or if you are using intellij will tell u too initailise the property before using it
    // And if you run it then it will throw an error "the variable must be initailised. 
   
    
    // functions are the same as they are declared outside the class 
    fun swim() { // member function
        
    }
    
    fun eat(food: Food) {
        
    }

    // string representation of the object
    override fun toString(): String {
        return  """Fish Two status:
        Friendly : ${this.friendly}
        Size   : ${this.size}
        """.trimIndent()
    }
}

// if you want your primary constructor to be public.
// we can use private or non public visibility modifier before it but then we have to use or write  constructor keyword to declare the primary constructor.
// Also if we have annotation we still have to use or write the constructor keyword.
// eg.

class BookOne private constructor(val totalPages: Int){
    
}

// if you donat have any visibility modifiers and annotations then we can omit the constructor keyword.
class BookTwo(val totalPages: Int ) {
    
}



