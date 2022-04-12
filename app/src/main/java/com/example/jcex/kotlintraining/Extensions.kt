package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Extension
 */
fun String.hasSpaces(): Boolean {
    return this.find { it == ' ' } != null
}

@Composable
fun ExtensionStringHasSpaces() {
    val stringTest = "Does it have spaces?"
    CardContainer {
        Title(text = "String extension")
        Code(
            text = "fun String.hasSpaces(): Boolean {\n" +
                    "    return this.find { it == ' ' } != null\n" +
                    "}"
        )
        Text(text = "\"Does it have spaces?\".hasSpaces() -> ${stringTest.hasSpaces()}")
    }
}

/**
 * Extension limitations
 * Cannot access to private properties
 * Extensions function are resolved at compile time (based on the tipe of the variable)
 */
open class AquariumPlant(val color: String, private val size: Int)
class GreenLeafyPlant(size: Int) : AquariumPlant("green", size)

fun AquariumPlant.isRed() = color == "red"  //OK
//fun AquariumPlant.isBig() = size > 50   //NO

fun AquariumPlant.print() = "AquariumPlant"
fun GreenLeafyPlant.print() = "GreenLeafyPlant"

val plant = GreenLeafyPlant(size = 10)
val aquariumPlant: AquariumPlant = plant    //this will stamps AquariumPlant!


@Composable
fun ExtensionLimitation() {
    CardContainer {
        Title(text = "Extension limitations")
        Code(text = "class AquariumPlant(val color: String, private val size: Int)")
        Text(text = "fun AquariumPlant.isRed() = color == \"red\"   // OK")
        TextUncorrect(text = "fun AquariumPlant.isBig() = size > 50")
        SmallSpacer()

        Code(
            text = "class GreenLeafyPlant(size: Int) : AquariumPlant(\"green\", size)\n" +
                    "fun AquariumPlant.print() = println(\"AquariumPlant\")\n" +
                    "fun GreenLeafyPlant.print() = println(\"GreenLeafyPlant\")\n" +
                    "\n" +
                    "val plant = GreenLeafyPlant(size = 10)\n" +
                    "val aquariumPlant: AquariumPlant = plant"
        )
        Text(text = "plant.print()-> ${plant.print()}")
        Text(text = "aquariumPlant.print() -> ${aquariumPlant.print()}")
        SmallSpacer()
        Note(text = "Extension functions are resolved statically, at compile time, based on the type of the variable")

    }
}

/**
 * Extension property
 * Kotlin let you add extension properties like that
 */
val AquariumPlant.isGreen: Boolean
    get() = color == "green"

@Composable
fun ExtensionProperty() {
    CardContainer {
        Title(text = "Extension property")
        Code(
            text = "val AquariumPlant.isGreen: Boolean\n" +
                    "    get() = color == \"green\""
        )
        Text(text = "aquariumPlant.isGreen -> ${aquariumPlant.isGreen}")
    }
}

/**
 * Nullable receivers
 * If you want to provide a default behavior when you function is applied to null.
 * In this case, there is no output when you run the program. Because plant is null,
 * the inner stringOutput(this) is not called.
 */
fun AquariumPlant?.pull() {
    this?.apply {
        stringOutput(this)
    }
}

fun stringOutput(aquariumPlant: AquariumPlant?): String = "removing $aquariumPlant"
val plantNullable: AquariumPlant? = null

@Composable
fun NullableReceivers() {
    CardContainer {
        Title(text = "Nullable receivers")
        Note(text = "The class you extend is called the receiver")
        Code(
            text = "fun AquariumPlant?.pull() {\n" +
                    "    this?.apply {\n" +
                    "        println(\"removing \$this\")\n" +
                    "    }\n" +
                    "}"
        )
        Text(text = "plantNull -> $plantNullable")
        Text(text = "plantNull.pull() -> ${plantNullable.pull()}")
        Note(
            text = "In this case, there is no output when you run the program. Because plant is null, the inner stringOutput(this) is not called."
        )
    }
}

@Composable
fun ExtensionsScreen() {
    LazyColumn {
        item { ExtensionStringHasSpaces() }
        item { ExtensionLimitation() }
        item { ExtensionProperty() }
        item { NullableReceivers() }
    }
}

@Preview
@Composable
fun ExtensionScreenPrev() {
    ExtensionsScreen()
}

