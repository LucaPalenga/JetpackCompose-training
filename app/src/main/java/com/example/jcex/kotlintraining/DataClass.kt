package com.example.jcex.kotlintraining

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        CardTitle(text = "Data class")

        Text(text = "decoration1 = $decoration1")
        Text(text = "decoration2 = $decoration2")
        Text(text = "decoration3 = $decoration3")
        Spacer(modifier = Modifier.size(4.dp))

        /**
         *  equals() method is provided by data classes
         */

        /**
         *  equals() method is provided by data classes
         */
        Text(text = "equals() method is provided by data classes", fontWeight = FontWeight.SemiBold)
        Text(text = "decoration1.equals(decoration2) = ${decoration1.equals(decoration2)}")    //false
        Text(text = "decoration3.equals(decoration2) = ${decoration3.equals(decoration2)}")    //true
        Spacer(modifier = Modifier.size(4.dp))

        /**
         * STRUCTURAL EQUALITY
         * In Kotlin, using == on data class objects is the same as using equals()
         */

        /**
         * STRUCTURAL EQUALITY
         * In Kotlin, using == on data class objects is the same as using equals()
         */
        Text(text = "Structural equality", fontWeight = FontWeight.SemiBold)
        Text(text = "decoration1 == decoration2 = ${decoration1 == decoration2}")    //false
        Text(text = "decoration3 == decoration2 = ${decoration3 == decoration2}")    //true
        Spacer(modifier = Modifier.size(4.dp))

        /**
         * REFERENTIAL EQUALITY
         * If you need to check whether two variables refer to the same object use the === operator
         */

        /**
         * REFERENTIAL EQUALITY
         * If you need to check whether two variables refer to the same object use the === operator
         */
        Text(text = "Referential equality", fontWeight = FontWeight.SemiBold)
        Text(text = "decoration1 === decoration2 = ${decoration1 === decoration2}")    //false
        Text(text = "decoration3 === decoration2 = ${decoration3 === decoration2}")    //false
        Spacer(modifier = Modifier.size(4.dp))

        /**
         * CLONE
         * To copy the contents to a new object, use the copy() method.
         */
        val decoration4 = decoration1.copy()
        Text(text = "Copy", fontWeight = FontWeight.SemiBold)
        Text(text = "decoration4 = decoration1.copy()")
        Text(text = "decoration1 == decoration4 = ${decoration1 == decoration4}")    //true
        Text(text = "decoration1 === decoration4 = ${decoration1 === decoration4}")    //false
        Spacer(modifier = Modifier.size(4.dp))

        /**
         * DESTRUCTURING
         * Assign all properties to variables
         */
        Text(text = "Destructuring", fontWeight = FontWeight.SemiBold)
        val decoration5 = Decoration2("crystal", "wood", "diver")
        Text(text = "decoration5 = $decoration5")

        val (rock, wood, diver) = decoration5
        Text(text = "(rock, wood, diver) = decoration5")
        Text(text = "rock= $rock")
        Text(text = "wood = $wood")
        Text(text = "diver = $diver")
        CardSubtitle(
            text = "NB: if you don't need one or more of the properties you can skip by using _ - (rock, _, diver) = decoration5"
        )
    }
}

@Composable
fun ClassScreeen() {
    LazyColumn {
        item { InterfaceDelegation() }
        item { DataClass() }
        item { EnumClass() }
    }
}

@Preview
@Composable
fun DataClassPrev() {
    DataClass()
}
