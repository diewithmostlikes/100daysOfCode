package annotation

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation

/**annotations are means to add metaData to the code
 * annotations are read by the compiler and used to generate code and logic
 * annotations are written right before the thing to annotate
 * annotations can be used with the functions, methods, classes and even with the control structures.
 * some annotations can also take arguments
 * */

// declaring  class annotation
annotation class Info

// creating class and using our annotation
@Info class Button(val label: String) {

    fun onClick(){
        println("hey $label button is clicked !")
    }
}

val classObj = Button::class

// lets get class information by using annotation
fun reflection () { for  (method in classObj.declaredMemberFunctions) println(method.name) }


fun printAllAnnotations(){
    val annotations = classObj.annotations
    for (annotation in annotations) println(annotation.annotationClass.simpleName)
}

fun main(){
    // printing all methods
    println("Methods in Button class :")
    reflection()

    // printing all annotations
    println("Button class all annotations:")
    printAllAnnotations()

    // finding a annotations
     val annotated = classObj.findAnnotation<Info>()
    annotated.apply{ println("Annotation found")}

}