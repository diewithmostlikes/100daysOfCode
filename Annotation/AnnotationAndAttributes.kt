/** Annotations are means of attaching meta data to code*/

// declare an annotation
annotation class MyAnnotationOne

/** specifying additional attributes by annotating the annotation class with meta annotation */
@Target(AnnotationTarget.CLASS) //specifies the possible kinds of elements which  can be annotated with annotation
annotation class AnnotationForClass

// Applying annotation
@AnnotationForClass
class EmptyClassOne

//Error -> @AnnotationForClass : because this Annotation is only for Classes
fun testFunction() { }

// Annotation for both class and Function
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class AnnotationForClassAndFunction

// Applying annotation

@AnnotationForClassAndFunction // works
class EmptyClassTwo

@AnnotationForClassAndFunction // works too
fun testFunctionTwo() { }


// Annotation for Parameters
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class AnnotationForParameter

// Applying Annotation
class TestClassOne(@AnnotationForParameter val testProp: String)
fun testFunctionThree(@AnnotationForParameter param: Int) { }

// Annotation for Expression and @Retention annotation or attribute for Annotation class
@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE) // specifies weather the annotation is specified in the compiled class files and weather its visible through reflection at runtime by default they both are true
annotation class AnnotationForExpression

// Applying annotation
fun testFunctionFour(): Int = @AnnotationForExpression 10
// or
fun testFunctionFive(): Int {
    return (@AnnotationForExpression 10)
}

// @Repeatable meta annotation on Annotation Class
annotation class WithoutRepeatAnnotation

@Repeatable// allow using the same annotation on a single elements multiple times
@Retention(AnnotationRetention.SOURCE)
annotation class RepeatableAnnotation

// Applying annotation

// Not Repeatable Annotation
@WithoutRepeatAnnotation
// Error -> @WithoutRepeatAnnotation : Because annotation is not repeatable
class EmptyClassFive

// Repeatable Annotation
@RepeatableAnnotation
@RepeatableAnnotation
class EmptyClassSix

// Annotation  present in documentation
@MustBeDocumented // specifies the annotation is part of public API and should be included in the class or Method signature shown in generated API Documentation
annotation class AnnotationPresentInDocumentation

// Applying Annotation
@AnnotationPresentInDocumentation
class EmptyClassSeven

@AnnotationPresentInDocumentation
fun testFunctionSix() { }


// if we need to annotate the primary constructor of the class we need to annotate it before the constructor and use constructor declare primary constructor
@Target(AnnotationTarget.CONSTRUCTOR) annotation class AnnotationForConstructor
class TestClassTwo @AnnotationForConstructor constructor (val prop: Int)


// also annotate property accessors (getter and setter methods)
@Target(AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
annotation class AnnotationForPropertyAccessors

class TestClassThree {
    var propOne: Int? = null
        @AnnotationForPropertyAccessors set(value) { field = 10}
        @AnnotationForPropertyAccessors get() = field
}














