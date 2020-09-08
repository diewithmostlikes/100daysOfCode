import kotlin.reflect.KClass

/** Annotations may have constructors that take Parameters */

// annotation class with constructor
annotation class AnnotationWithConstructor(val param: String) // annotation parameter must be val

// parameter allowed types are

// 1. types that correspond to java primitive type(Int, Long etc)

// 2. String
annotation class AnnotationWithStringPara(val stringPara: String)
@AnnotationWithStringPara("Empty Class") class EmptyClass
@AnnotationWithStringPara("sample function !") fun sampleFunctionOne() { }

// 3. enums
enum class Enum{TEST1, TEST2, TEST3, TEST4, TEST5}
annotation class AnnotationWithEnumParam(val enum: Enum)

// 4. use another annotation as param of annotation
annotation class MyAnnotation
annotation class AnnotationWithAnnotationParam(val otherAnnotation: MyAnnotation)

// using
// if we are using annotation as param of another annotation its name is not prefixed with '@' character
@AnnotationWithAnnotationParam(MyAnnotation()) class TestClassFour

// 3. classes
// if you need to specify class as argument of an annotation use KClass
// kotlin compiler will automatically convert it to java Class so java code can access the annotation and arguments easily
annotation class AnnotationWithClassParam(val classOne: KClass<*>, val classTwo: KClass<Int>)

// using
@AnnotationWithClassParam(String::class, Int::class) class TestClassFive

// array of the types above
annotation class AnnotationWithArrayParam(val stringArray: Array<String>)
val stringArray = arrayOf("kotlin", "Google", "jetbrains")

// Error -> @AnnotationWithArrayParam(stringArray) class TestClassSeven : because  annotation argument must be compile time constant.

@AnnotationWithArrayParam(arrayOf("kotlin", "google", "jetbrains")) class TestClassSix
// or by using array literal
@AnnotationWithArrayParam(["kotlin", "google", "jetbrains"]) class TestClassSeven

