package basics.sum_up

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

// so lets practice CoroutineContext

/**
 * CoroutineContext is a set of elements that defines the behaviour of a coroutine
 * and those elements are :
 * 1. CoroutineDispatcher -: Dispatches work to correct thread ( Responsible for the thread that your coroutine will run on.)
 * 2. CoroutineName -: name for the Coroutine (used to make debugging easier).
 * 3. CoroutineJob : controls the Lifecycle of the Coroutine.
 * 4. CoroutineExceptionHandler -: Handel Uncaught Exceptions.
 * */

fun newCoroutineScope() {
    // lets create context
    val context: CoroutineContext = Dispatchers.Default + Job() + CoroutineName("MyCoroutines")
    // by getting instance of CoroutineScope class (getting CoroutineScope)
    val scope = CoroutineScope(context) // it requires a context

    // now we have our scope we can launch coroutines in it
    scope.launch { println("Coroutine 1")}
    scope.launch { println("Coroutine 2")}
    /*......as much as you like :-) .....*/

    // the context of these newly launched coroutine will be inherited from its parent(Context of parent coroutine or Context of CoroutineScope it self.)
    // but CoroutineContext of a children and CoroutineContext of parent( coroutine or CoroutineScope) can be different.

    // CoroutineContext of a parent is Calculated using
    // defaults + inherited CoroutineContext + arguments
    /**
     * 1. Defaults like
     * CoroutineName -: "coroutine"
     * CoroutineDispatcher -: Dispatchers.Default
     * 2.Inherited CoroutineContext -: the context of parent coroutine or CoroutineScope that created the new Coroutine.
     * 3.Arguments -: passed in the coroutine builder and replace the related types or values in inherited CoroutineContext
     */

    // CoroutineContext of new Coroutine
    // parent CoroutineContext + Job()
    // note new instance of job will be created.
    // so we can handle it individually

}
