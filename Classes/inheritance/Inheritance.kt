package inheritance// so today i am learning inheritance

// lets create a class softwareProgrammer

class SoftwareProgrammer(experience: Int, favLang: String) {


    fun code() { }

    fun drinkCoffee() {}
}

/** just like  above class we can create different class for every type of programmer.
 * but the common  operations  like code() and properties like favLang will going to be same in evrey class
 * and we have to write  it over and over......
 * so instead before creating any type of programmer class we can create a higher class and inherit its properties and
 * method in   different types of programmer classes
 */

// eg
// by default classes are final in kotlin. (they can't be inherited)
// So Class must be declared as open if you want to inherit from it. (to make a class inheritable)
open class Programmer(var favLang: String, programmerType: String, var experience: Int) { // deep down we all are programmers no matter what type area in programing world we are donating

    val knowLanguages: MutableList<String> = mutableListOf(favLang)

    fun code(languageName:String = favLang, time: Int = 2) { // code in <languageName> and code for <time> h (default parameters)
        println("lets' ")
        println("lets get into it im going to code for $time ")
        experience += time
    }

    // method must be declared as open if you want them to be overridden in subclasses or child Classes
    open fun drinkCoffee(cup: Int  = 1) { // by default drink 1 cup
        println("woof... so much to do today lets drink coffee")
        if (cup > 1) println("Drinking $cup Cups") else println("Drinking $cup Cup")
    }

    fun learnNewLanguage(langName: String) { knowLanguages.add(langName) }

    fun changeFavLang(langName:String) {
        if (langName !in knowLanguages ) println("Please first learn $langName then you can change it your favorite language ! ")
        else favLang = langName
    }

}
// okay i think these are the some common properties and method (actions) a programmer do.
// Now we don't have to specify each and every method and properties while creating different types of programmes Classes.
// we can just inherit the properties and methods of programmer class this is called inheritance


// Lets create Web Developer class
class WebDeveloper(favLanguage: String, experience: Int):
        Programmer(favLanguage, "Web-Developer", experience) { // Note programmer class must be open to inherit it.

    // now here we can write some web developer specific  properties and methods

    fun meetWithClient(client: Client) {
        println("Meeting with inheritance.Client ${client.name}")
    }

    fun startCode(language: String  = "", timeForCode: Int = 2) {

        if (language.isBlank()) { code(time = timeForCode)  } else code(language, timeForCode)  // calling higher class ( programmer ) or super class ( inheritance.Programmer ) method code()


    }
    // we can also access the method and properties of super or parent class by using super keyword

    fun aboutMe() {
        println("""
            Favorite Language : ${super.favLang} // Accessing parent class properties using super keyword
            Total Experience : ${super.experience}
            Know Languages : ${super.knowLanguages}
        """.trimMargin())
    }

    // we can also access the methods of parent class by using supper keyword
    fun startCodeTwo(language: String, timeForCode: Int) {
        super.code(language, timeForCode) // accessing method of parent class by using supper keyword

        // we must have to pass arguments to the method which contains default arguments if we are using super to access parent class or base class methods
        // Error-: super.code() // we must pass arguments to code even if it contains default parameters or arguments or not.
        // super calls with default arguments is not allowed
    }

    inner class Mind {
        fun learn(languageName: String) {
            super@WebDeveloper.learnNewLanguage(languageName) // accessing outer class's  parent or base class method using super.
        }
    }


    // we can also override the methods declared in the parent class
    // for doing so we need to write 'override' modifier before the 'fun'

    // when we override a method function its function signature must match.
    override fun drinkCoffee(cup: Int) { // when we override a method or function we can't set default value to its parameters (Error-: cups: Int =10)
        println("Nah web developers don't like coffee") // for example don't get offended
    }


}

// creating empty class inheritance.Client
data class Client(val name:String) // sample data class to hold the name of the client







