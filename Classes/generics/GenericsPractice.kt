package generics

// lets say we are importing some eatable Things
interface Eatable {
    fun okToEat(): Boolean
}
class Fruit(val name: String, private val decay: Boolean = false) : Eatable {
    override fun okToEat() =!decay

    override fun toString(): String {
        return name
    }
}

class Vegetable(val name: String, private val decay: Boolean = false) : Eatable{
    override fun okToEat() = !decay
}

class OtherFood(val name: String, private val decay: Boolean = false): Eatable {
    override fun okToEat() = !decay
}

class Importer<T: Eatable>(private val foodList: List<T>) {

    private var foodChecked: Boolean = false
    private var loadedToTruck: Boolean = false
    private val checkedFood: MutableList<T>  = mutableListOf()
    private val wasteFood: MutableList<T> = mutableListOf()
    private val foodLoadedInTruck: MutableList<T> = mutableListOf()

    fun loadToTruck(){
        if (!foodChecked) println("Food must be checked before loading to the truck ! ")
        else {
            println("***************************Loading to Truck**************************")
            // loading food to truck..
            for (food in checkedFood ) {
                foodLoadedInTruck.add(food)
                println("$food is loaded ")
            }
            loadedToTruck = true
            println("Truck loaded.")

        }
    }

    fun unloadFromTruck(){
        if (!loadedToTruck) println("Nothing in the Truck to unload !")
        else {
            println("************************Unloading********************************** ")
            while(foodLoadedInTruck.count() > 0){
                val food = foodLoadedInTruck.removeAt(0)
                println("$food unloaded..")
            }
        }
    }

    fun foodCheck(){
        println("**********************Food is checking*********************8")
        for (food in foodList) {
            // Checking food ok to eat
            if (food.okToEat())  {
                println("$food is checked and ok to Eat")
                checkedFood.add(food)
            }
            else {
                wasteFood.add(food)  // Food will not be imported
                println("$food is decay can't import.. and added to waste..")
            }
        }
        println("Check Done.")
        foodChecked = true // food is checked now
    }

    fun importDetails() {
        println("************************** Import Details **************************")
        println("SuccessFully Imported: $checkedFood")
        println("Wasted Food: $wasteFood")
    }

}
fun main(){
    val importer: Importer<Fruit> = Importer(listOf(Fruit("Apple"), Fruit("Grapes")))
    importer.foodCheck()
    importer.loadToTruck()
    importer.unloadFromTruck()
    importer.importDetails()
}