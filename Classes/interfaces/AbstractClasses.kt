package interfaces

// empty class for illustration
class OperatingSystem(val name: String)



/** We want to make sure that every type of computer should have a operating system
 *  and a method  to change that operating system
 *  To do so kotlin provides two ways to do that...
 *  1> by abstract classes and
 *  2> by interfaces
 *
 *  */
// creating a abstract class
// Note we can'nt create instance of the abstract class
abstract class Computer(val ram: Int ) { // abstract class have constructor
    // we  don't know what type of computer is going to install what operating system now
    // so we declare it abstract here so the subclasses can initialise it letter according to need
    abstract  val installedOs: OperatingSystem

    // some basic operations of computers
    fun turnOn(){ println("turning on....")}
    fun turnOff() { println("turning Offf....")}

    // we really can't define now what Os user will install in the computer and how it is installed...
    // so we declare installOs() as abstract method so the subclasses can implement this according to the situation.
    abstract  fun installOs(os: OperatingSystem)
}


// Lets create a apple laptop
class AppleLaptop(ram: Int): Computer(ram) {
    // now here we have to initialise and implement methods that are declared as abstract in base class
    // or we have to declare it as abstract
    // so i want this class as concrete class(means all the abstract members are implemented here)


    // Implementing abstract members...
    override var installedOs: OperatingSystem = OperatingSystem("Macintosh") // initialised the abstract property  or state
    override fun installOs(os: OperatingSystem) {
        println("Installing ${os.name}")
        installedOs = os
    }


    // now this class is concrete class  because  we have implemented all the abstract methods and initialised all the abstract properties .

}

// lets check how many abstract classes we can implement in one classs
abstract class AbsOne
abstract class AbsTwo
abstract class AbsThree

//Error- : class ConcreteClass: AbsOne(), AbsTwo(), AbsThree()
// so yup we can only implement one class at a time

// one more thing we can do with abstract class or interfaces
// we can create functions that will only do things that is defined in the interface or abstract class
// nothing more and nothing less
fun info(comp: Computer) {
    println(comp.ram)
    println(comp.installedOs)

}

fun main() {
    val appleLaptop: AppleLaptop = AppleLaptop(8)
    // note AppleLaptop is Implementing abstract class Computer
    // so we can pass it to this function
    info(appleLaptop)


}


