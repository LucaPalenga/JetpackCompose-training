package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Labeled breaks
 * A break qualified with a label jumps to the execution point right after the loop marked with that label
 * NB: A labeled break in Kotlin can only be used to break out of loops, unlike goto in C++ or C#
 */
fun labels(): String {
    var rt: String = ""
    outerLoop@ for (i in 1..100) {
        rt += "$i"
        for (j in 1..100) {
            if (i > 10) break@outerLoop
        }
    }
    return rt
}

@Composable
fun LabeledBreaks() {
    CardContainer {
        Title(text = "Labeled breaks")
        Note(text = "A break qualified with a label jumps to the execution point right after the loop marked with that label")
        Code(
            text = "fun labels(): String {\n" +
                    "    var rt: String = \"\"\n" +
                    "    outerLoop@ for (i in 1..100) {\n" +
                    "        rt += \$i\"\n" +
                    "        for (j in 1..100) {\n" +
                    "            if (i > 10) break@outerLoop\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return rt\n" +
                    "}"
        )
        Note(text = "NB: A labeled break in Kotlin can only be used to break out of loops, unlike goto in C++ or C#")
    }
}

/**
 * High-order function
 * Passing a lambda or other function as an argument to a function creates a higher-order function
 */
data class Fish(var name: String)

/**
 * The with() function lets you make one or more references to an object or property in a more compact way.
 * Using this.with() is actually a higher-order function, and in the lamba you specify what to do with the supplied object.
 */
fun withExample(): String {
    val fish = Fish("splashy")
    with(fish.name) {
        return this.capitalize()
    }
}

/**
 * create high-order function
 * fun myWith(name: String, block: String.() -> Unit) {}
 */
fun myWith(name: String, block: String.() -> String): String {
    return name.block()
}

val fish = Fish("splashy")

fun myWithExample(): String {
    return myWith(fish.name) {
        this.capitalize()
    }
}

@Composable
fun HighOrderFunction() {
    CardContainer {
        Title(text = "High-order function")
        Note(text = "Passing a lambda or other function as an argument to a function creates a higher-order function")
        Code(text = "data class Fish(var name: String)")
        Code(
            text = "fun withExample(): String {\n" +
                    "    val fish = Fish(\"splashy\")\n" +
                    "    with(fish.name) {\n" +
                    "        return this.capitalize()\n" +
                    "    }\n" +
                    "}"
        )
        Text(text = "withExample() -> ${withExample()}")

        SmallSpacer()
        TextSemibold(text = "Create myWith() (an higher-order function)")
        Code(text = "fun myWith(name: String, block: String.() -> Unit) {}")
        Note(text = "replacing with() with myWith() in withExample()")
        Text(text = "withExample() -> ${myWithExample()}")
    }
}

/**
 * Explore more built in extensions
 * Here are a few of the others you might find handy: run(), apply(), and let()
 *
 * The run() function is an extension that works with all types.
 * It takes one lambda as its argument and returns the result of executing the lambda
 *
 * The apply() function is similar to run(), but it returns the changed object it was applied to instead of the result of the lambda.
 * This can be useful for calling methods on a newly created object.
 *
 * The let() function is similar to apply(), but it returns a copy of the object with the changes.
 * This can be useful for chaining manipulations together.
 */

val fishApply = Fish(name = "splashy").apply {
    name = "sharky"
}

val fishLet = fish.let { it.name.capitalize() }
    .let { it + "fish" }
    .let { it.length }
    .let { it + 31 }


@Composable
fun ExploreBuiltInExtensions() {
    CardContainer {
        Title(text = "Explore more built in extensions")
        Note(text = "Here are a few of the others you might find handy: run(), apply(), and let()")

        SmallSpacer()
        TextSemibold(text = "run()")
        Note(text = "It is an extension that works with all types. It takes one lambda as its argument and returns the result of executing the lambda")
        Code(text = "fish.run{ name } -> ${fish.run { name }}")

        SmallSpacer()
        TextSemibold(text = "apply()")
        Note(text = "It returns the changed object it was applied to instead of the result of the lambda. This can be useful for calling methods on a newly created object.")
        Code(
            text = "Fish(name = \"splashy\").apply { name = \"sharky\" } -> ${fishApply}"
        )
        Note(text = "run() and apply() are similar, but run() returns the result of applying the function, and apply() returns the object after applying the function.")

        SmallSpacer()
        TextSemibold(text = "let()")
        Note(text = "It returns a copy of the object with the changes. This can be useful for chaining manipulations together")
        Code(
            text = "fish.let { it.name.capitalize() }\n" +
                    "    .let { it + \"fish\" }\n" +
                    "    .let { it.length }\n" +
                    "    .let { it + 31 } -> ${fishLet}"
        )
        Note(text = "Printing fish after calling let() you will see that anything is changed")
        Code(text = "fish -> $fish")
    }
}

/**
 * Inline functions
 * Lambdas are objects. A lambda expression is an instance of a Function interface, which is itself a subtype of Object.
 * The Function interface has a method, invoke(), which is overridden to call the lambda expression.
 */
//this creates an object
val fishObj = myWith2(fish.name, object : Function1<String, Unit> {
    override fun invoke(name: String) {
        name.capitalize()
    }
})

fun myWith2(name: String, block: String.() -> Unit) {
    name.block()
}

/**
 * If you're defining something like myWith() that you use everywhere, the overhead could add up
 * Marking a function as inline means that every time the function is called, the compiler will actually transform the source code to "inline" the function.
 * That is, the compiler will change the code to replace the lambda with the instructions inside the lambda.
 */

@Composable
fun InlineFunctions() {
    CardContainer {
        Title(text = "Inline functions")
        Note(text = "Lambdas are objects. A lambda expression is an instance of a Function interface, which is itself a subtype of Object.")
        Note(text = "The Function interface has a method, invoke(), which is overridden to call the lambda expression.")
        Code(
            text = "with(fish.name, object : Function1<String, Unit> {\n" +
                    "    override fun invoke(name: String) {\n" +
                    "        name.capitalize()\n" +
                    "    }\n" +
                    "})"
        )
        Note(text = "It creates an object.")
        Note(
            text = "If you're defining something like myWith() that you use everywhere, the overhead could add up" +
                    "Marking a function as inline means that every time the function is called, the compiler will actually transform the source code to \"inline\" the function." +
                    "\nThat is, the compiler will change the code to replace the lambda with the instructions inside the lambda."
        )
        Code(
            text = "inline myWith(fish.name) {\n" +
                    "    capitalize()\n" +
                    "}"
        )
        Note(text = "So marking myWith() with inline transform the call in inline call (cause compiler ")
        Code(text = "fish.name.capitalize()")
    }
}

/**
 * Single abstract methods
 * Some examples are Runnable, which has a single abstract method, run(),
 * and Callable, which has a single abstract method, call()
 * Es:
 * public class JavaRun {
 *   public static void runNow(Runnable runnable)
 *   {
 *     runnable.run();
 *   }
 * }
 *
 * Kotlin lets you instantiate an object that implements an interface by preceding the type with object:
 */
//val runnable = object: Runnable {
//    override fun run() {
//        println("I'm a Runnable")
//    }
//}
val runnable = Runnable { println("I'm a Runnable") }

@Composable
fun SingleAbstractMethods() {
    CardContainer {
        Title(text = "Single Abstract Methods")
        Note(text = "Some examples are Runnable, which has a single abstract method, run(), and Callable, which has a single abstract method, call()")
        Code(
            text = "public class JavaRun {\n" +
                    "  public static void runNow(Runnable runnable) {\n" +
                    "    runnable.run();\n" +
                    "  }\n" +
                    "}"
        )
        Note(text = "Kotlin lets you instantiate an object that implements an interface by preceding the type with object:")
        Code(
            "object : Runnable {\n" +
                    "    override fun run() {\n" +
                    "        println(\"I'm a Runnable\")\n" +
                    "    }"
        )
        Note(text = "Kotlin provide a simple way to do that, use a lambda in place of the object to make this code a lot more compact")
        Code(text = "Runnable { println(\"I'm a Runnable\") }")
    }
}

@Composable
fun FunctionalManipulationScreen() {
    LazyColumn {
        item { LabeledBreaks() }
        item { HighOrderFunction() }
        item { ExploreBuiltInExtensions() }
        item { InlineFunctions() }
        item { SingleAbstractMethods() }
    }
}

@Preview
@Composable
fun FunctionalManipulationPrev() {
    FunctionalManipulationScreen()
}