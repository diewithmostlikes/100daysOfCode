package sealed_class

// In enum classes enum constant exists as an single instances
enum class Os{
    WINDOWS,
    LINUX,
    ANDROID,
    IOS,
    MACINTOSH,
}

// i have written some os names as enum constants and each enum constant is instance itself
// so i can have only single instance of Any enum constant in "Os" enum class

// but as we know operating systems also have versions like windows 10, IOs 13, android pie etc
enum class OsWithVer(val version: String){
    WINDOWS("windows 10"),
    LINUX("kali"),
    ANDROID("pie"),
    IOS("IOS 11"),
    MACINTOSH("i don't know "),
}
// i can do that but the versions will always  going to remain the same

// i can do that with open or abstract class
abstract class OsWithVerTwo(val version: String)
// i have used short names because i wanted perfect and full names to illustrate in sealed class example bellow
class Win(version: String): OsWithVerTwo(version)
class Ios(version: String): OsWithVerTwo(version)
class Lin(version: String): OsWithVerTwo(version)

// its done but we lose the power or feature that  enum class provides
// Which is to have a limited possible values
fun osDetailsA(os: OsWithVerTwo) {
    // val details: String = when(os) { }

    // now to use when here as above... i have to add each and every case with a else branch
    // So Like enum classes, here when don't know about how many limited possible cases there are..
    // and if i forget to mention any case i will have to add else branch.

    // but suppose i have forget 2 or 3 cases
    // and thus for those 2 and 3 cases the same else branch is going to work...
    // which would lead to unwanted behavior.... for every case i have forgot to write.

}

/** Here our sealed class come in the picture
 * when we need limited possible values like enum class but also want different instances of those possible values.
 */

// Note sealed classes are abstract by default
// and thus we can't instantiated directly
// one more thing sealed class constructor can't be non private.
sealed class OsWithVersions(val version: String)

// lets create subclasses of sealed class
// Note subclasses of sealed class must be in the same file in which sealed class is declared
// but classes that inherit subclass of sealed class can be in different places or files.
class Windows(version: String): OsWithVersions(version)
class IOS(version: String): OsWithVersions(version)
class Linux(version: String): OsWithVersions(version)
class Android(version: String): OsWithVersions(version)

// now we limited Possible values but we can create different instances of them
// as in this example now we have limited possible Operating System names but we can change there versions
// by creating multiple instances of the sub class of sealed class.

// creating function that takes Sealed class Subclasses and print the OS name and the version
fun osDetails(os: OsWithVersions): String =
        when(os){
            is Windows -> "Windows : ${os.version}"
            is IOS -> "IOS : ${os.version}"
            is Linux -> "Linux : ${os.version}"
            is Android -> "Android : ${os.version}"

            // like Enum class when knows that all the cases have discussed above
            // and thus we don't need to specify else branch
            // and as when knows how many subclasses a sealed class have we can use our ide help to write all the cases in when block
        }

// now just like enum we can have properties and methods in sealed class
// we can declare some members as abstract soo every subclass of enum class has to implement them
sealed class A {
   abstract fun abstractMethod()
    fun methodOne() {
        println(" simple method")
    }
}

class ImplA(val prop: Int) : A() {
    override fun abstractMethod() {
        println("Implemented")
    }
}


fun main() {
    // lets create instance of Android  os
    val androidQ: Android = Android("Q")
    println(osDetails(androidQ))




}




