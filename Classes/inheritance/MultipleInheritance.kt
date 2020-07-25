package inheritance

// So far we have talked about inheritance its rules and its two common types
// Lets practice another type of inheritance here...

// Multiple Inheritance.


// creating interfaces

// common human actions
interface HumanActions{
    fun eat()
    fun talk()
    fun walk()
    fun run()
    fun sleep()
}


// some robot actions
interface RobotActions {
    fun talk()
    fun recordVision()
    fun solveProblems()
    fun scan()


}

interface HumanInfo {
    val name : String
    val age : Int
}

interface RobotInfo {
    val robotId: Int
    val robotCpuFreq: Double
    val robotMemory: Int
}


// creating a class HumanBeing
// initialized the properties of HumanInfo interface in primary constructor
class HumanBeing( override val name: String,  override val age: Int): HumanInfo, HumanActions{

    // common human behaviors
    override fun eat() { println("Eating......") }
    override fun talk() { println("Talking......") }
    override fun walk() { println("Walking.....") }
    override fun run() { println("Running......") }
    override fun sleep() { println("Good Night... Sleeping...") }

}

// now lets  create a class Robot
class Robot(id: Int,  cpuFreq: Double,  memory: Int): RobotActions, RobotInfo{

    override val robotId: Int = id
    override val robotCpuFreq: Double  = cpuFreq
    override val robotMemory: Int = memory
    // declaring some  member methods(come basic actions done by a robot)
    override fun talk() { println("talking in robotic voice....") }
    override fun recordVision(){ println(" recording what im seeing.....")}
    override fun solveProblems(){ println("solving the problem.....") }
    override fun scan(){ println("Scanning  environment around me ...!!!") }

    fun about(): String{
        return """
            ID: $robotId
            CPU Frequency: $robotCpuFreq GH
            Memory: $robotMemory TB
        """.trimIndent()

    }

}

// Now Im a mad scientist and like before i fused aliens and human and created Aliuman
// Just like that i want to create the combination of human and robot yesssss... im going to create a cyborg this time
// but kotlin and java both does't support the multiple inheritance... so how we can get features of both classes

// for doing so lets leave this code here for some time and i will learn about abstract classes and interfaces first.Then i will come back and create our cyborgs

/** soo back here now  after learning interfaces and interface delegation i have some better idea to create our cyborg
 * A cyborg is which have features of human and a robot
 * we are going to use interface delegation to add robot and human features to our cyborg class
 * do so we have to create some interfaces and implement themlets do it

*/


class Cyborg(private val human: HumanBeing, private val robot: Robot):
        HumanActions by human,
        RobotActions by robot,
        HumanInfo by human,
        RobotInfo by robot{



    // now we have both HumanBeing and Robot features in our Cyborg class
    // in short we have created cyborgs

    fun about(): String {
       return """
           Name:$name-$robotId
           Cpu: $robotCpuFreq
           memory: $robotMemory
           Age: $age
       """.trimIndent()
    }

    // must override the talk() method because HumanBeing and Robot both providing implementation of talk ()
    override fun talk() {
        human.talk()
        robot.talk()
    }


}


fun main(){
    /// huff lets create our cyborg now..........

    // creating a human
    val alex: HumanBeing  = HumanBeing("alex", 19)

    // creating a robot
    val robotOne: Robot = Robot(100, 20.5, 1000)


    // now time to create a cyborg
    val firstCyborg: Cyborg = Cyborg(alex, robotOne)
    println(firstCyborg.about())

}