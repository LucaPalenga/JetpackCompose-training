package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ClassScreeen() {
    LazyColumn {
        item { InterfaceDelegation() }
        item { DataClass() }
        item { EnumClass() }
    }
}

/**
 * Kotlin data class objects have some extra benefits, such as utilities for printing and copying
 */
data class Decoration(val rocks: String)

/**
 * DESTRUCTURING
 * Here is a data class with 3 properties.
 */
data class Decoration2(val rocks: String, val wood: String, val diver: String)


@Composable
fun DataClass() {
    val decoration1 = Decoration("granite")
    val decoration2 = Decoration("slate")
    val decoration3 = Decoration("slate")

    CardContainer {
        Title(text = "Data class")
        Code(text = "val decoration1 = Decoration(\"granite\") -> $decoration1")
        Code(text = "val decoration2 = Decoration(\"slate\") -> $decoration2")
        Code(text = "val decoration3 = Decoration(\"slate\") -> $decoration3")
        SmallSpacer()
        Note(text = "Kotlin data class objects have some extra benefits, such as utilities for printing and copying")
        SmallSpacer()

        /**
         *  equals() method is provided by data classes
         */
        TextSemibold(text = "equals() method is provided by data classes")
        Text(text = "decoration1.equals(decoration2) -> ${decoration1.equals(decoration2)}")    //false
        Text(text = "decoration3.equals(decoration2) -> ${decoration3.equals(decoration2)}")    //true
        SmallSpacer()

        /**
         * STRUCTURAL EQUALITY
         * In Kotlin, using == on data class objects is the same as using equals()
         */
        TextSemibold(text = "Structural equality")
        Text(text = "decoration1 == decoration2 -> ${decoration1 == decoration2}")    //false
        Text(text = "decoration3 == decoration2 -> ${decoration3 == decoration2}")    //true
        SmallSpacer()

        /**
         * REFERENTIAL EQUALITY
         * If you need to check whether two variables refer to the same object use the === operator
         */
        TextSemibold(text = "Referential equality")
        Text(text = "decoration1 === decoration2 -> ${decoration1 === decoration2}")    //false
        Text(text = "decoration3 === decoration2 -> ${decoration3 === decoration2}")    //false
        SmallSpacer()
        /**
         * CLONE
         * To copy the contents to a new object, use the copy() method.
         */
        val decoration4 = decoration1.copy()
        TextSemibold(text = "Copy")
        Code(text = "decoration4 = decoration1.copy()")
        Text(text = "decoration1 == decoration4 -> ${decoration1 == decoration4}")    //true
        Text(text = "decoration1 === decoration4 -> ${decoration1 === decoration4}")    //false
        SmallSpacer()

        /**
         * DESTRUCTURING
         * Assign all properties to variables
         */
        TextSemibold(text = "Destructuring")
        val decoration5 = Decoration2("crystal", "wood", "diver")
        Code(text = "decoration5 = $decoration5")

        val (rock, wood, diver) = decoration5
        Text(text = "(rock, wood, diver) = decoration5")
        Text(text = "rock = $rock")
        Text(text = "wood = $wood")
        Text(text = "diver = $diver")
        SmallSpacer()
        Note(
            text = "NB: if you don't need one or more of the properties you can skip by using _ - (rock, _, diver) = decoration5"
        )
    }
}


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
        Title(text = "Enum class")
        Code(text = "Direction(degrees: Int)\n{ NORTH(0), SOUTH(180), EAST(90), WEST(270) }")
        SmallSpacer()
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
        Title(text = "Sealed class")
        Note(text = "This type of class can be subclassed only inside the file which it's declared")
        androidx.compose.material3.Text(
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

@Preview
@Composable
fun DataClassPrev() {
    DataClass()
}
