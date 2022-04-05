package com.example.jcex.kotlintraining

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Enum class
 * Like singleton class (object) there can be only one instance for each enumaration value
 */
enum class Direction(val degrees: Int) {
    NORTH(0), SOUTH(180), EAST(90), WEST(270)
}

@Composable
fun EnumClass() {
    CardContainer {
        CardTitle(text = "Enum class")
        CardSubtitle(text = "Direction(degrees: Int){ NORTH(0), SOUTH(180), EAST(90), WEST(270) }")
        Text("Direction.EAST.name -> ${Direction.EAST.name}")
        Text("Direction.EAST.ordinal -> ${Direction.EAST.ordinal}")
        Text("Direction.EAST.degrees -> ${Direction.EAST.degrees}")
    }
}

@Preview
@Composable
fun EnumClassPrev() {
    EnumClass()
}

/**
 * Sealed class
 * A sealed class is a class that can be subclassed, but only inside the file in which it's declared
 * Because the classes and subclasses are in the same file,
 * Kotlin will know all the subclasses statically.
 * That is, at compile time, the compiler sees all the classes and subclasses and knows that
 * this is all of them, so the compiler can do extra checks for you.
 */
sealed class Seal
class SeaLion : Seal()
class Walrus : Seal()

fun matchSeal(seal: Seal): String = when (seal) {
    is Walrus -> "walrus"
    is SeaLion -> "sea lion"
}

@Composable
fun SealedClass() {
    CardContainer {
        CardTitle(text = "Sealed class")
        CardSubtitle(text = "This type of class can be subclassed only inside the file which it's declared")
        Text(
            text = "sealed class Seal\n" +
                    "class SeaLion : Seal()\n" +
                    "class Walrus : Seal()"
        )
    }
}

@Preview
@Composable
fun SealedClassPrev() {
    SealedClass()
}