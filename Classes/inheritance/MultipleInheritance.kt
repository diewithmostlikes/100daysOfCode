package inheritance

// So far we have talked about inheritance its rules and its two common types
// Lets practice another type of inheritance here...

// Multiple Inheritance.

// creating a class HumanBeing
open class HumanBeing(val name: String, val age: Int){

    // common human behaviors
    fun eat() { println("Eating......") }
    fun talk() { println("Talking......") }
    fun walk() { println("Walking.....") }
    fun run() { println("Running......") }
    fun sleep() { println("Good Night... Sleeping...") }

}

// now lets  create a class Robot
open class Robot(val id: Int, val cpuFreq: Double, var memory: Int){

    // declaring some  member methods(come basic actions done by a robot)
    fun talk() { println("talking in robotic voice....") }
    fun recordVision(){ println(" recording what im seeing.....")}
    fun solveProblem(){ println("solving the problem.....") }
    fun scan(){ println("Scanning  environment around me ...!!!") }

    override fun toString(): String {
        return """
            ID: $id
            CPU Frequency: $cpuFreq GH
            Memory: $memory TB
        """.trimIndent()
    }
}

// Now Im a mad scientist and like before i fused aliens and human and created Aliuman
// Just like that i want to create the combination of human and robot yesssss... im going to create a cyborg this time
// but kotlin and java both does't support the multiple inheritance...
//  so how we can inherit two or more classes
// for doing so lets leave this code here for some time and i will learn about abstract classes and interfaces first.
// then i will come back and create our cyborgs



fun main(){


}