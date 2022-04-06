package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


/**
 * Generics
 * A generic type allows you to make a class generic, and thereby make a class much more flexible
 */
class MyList<T> {
    fun get(pos: Int): T {
        TODO("not yet implemented")
    }

    fun add(item: T) {

    }
}

open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)
class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}

class GenericAquarium<T>(val waterSupply: T)

val genAquarium = GenericAquarium(TapWater())
val genAquarium2 = GenericAquarium("string")
val genAquarium3 = GenericAquarium(null)

/**
 * Generics constraint
 * You can pass anything, it's possible by default beacause T stands for the nullable Any? type,
 * that's the type at the top of the hierarchy.
 * This is equivalent to previous GenericAquarium class declaration
 */
class GenericAquariumEquivalent<T : Any?>(val waterSupply: T)

//To not allow passing null you can make T of type Any
class GenericAquariumNotNull<T : Any>(val waterSupply: T)

//To make sure that only a WaterSupply can be passed for T
class GenericAquariumOnlyWaterSupply<T : WaterSupply>(val waterSupply: T) {
    /**
     * More checking
     * check() function is a standard library in Kotlin. It acts as an assertion and will throw an IllegalStateException if its argument evaluates to false
     */
    fun addWater() {
        check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
        //..
    }
}


@Composable
fun ExploreGenerics() {
    CardContainer {
        CardTitle(text = "Explore generics")
        CardSubtitleOSS(text = "A generic type allows you to make a class generic, and thereby make a class much more flexible")
        SmallSpacer()
        CardSubtitle(
            text = "open class WaterSupply(var needsProcessing: Boolean)" +
                    "\n" +
                    "class TapWater : WaterSupply(true) {\n" +
                    "    fun addChemicalCleaners() {\n" +
                    "        needsProcessing = false\n" +
                    "    }\n" +
                    "}" +
                    "\n" +
                    "class FishStoreWater : WaterSupply(false)\n" +
                    "class LakeWater : WaterSupply(true) {\n" +
                    "    fun filter() {\n" +
                    "        needsProcessing = false\n" +
                    "    }" +
                    "}" +
                    "\n" +
                    "class GenericAquarium<T>(val waterSupply: T)"
        )
        SmallSpacer()
        CardSubtitle(text = "val genAquarium = GenericAquarium(TapWater())")
        MediumSpacer()
        Text(text = "genAquarium.waterSupply.needsProcessing -> ${genAquarium.waterSupply.needsProcessing}")
        genAquarium.waterSupply.addChemicalCleaners()
        CardSubtitleOSS(text = "Because it is of type TapWater, you can call addChemicalCleaners() without any type casts")
        Text(text = "genAquarium.waterSupply.addChemicalCleaners()")
        Text(text = "genAquarium.waterSupply.needsProcessing -> ${genAquarium.waterSupply.needsProcessing}")

        CardSubtitleOSS(text = "But generic means you can pass almost anything")
        Text(text = "GenericAquarium(\"string\")")
        Text(text = "GenericAquarium(null)")
        CardSubtitleOSS(
            text = "Is possible by default beacause T stands for the nullable Any? type, that's the type at the top of the hierarchy." +
                    "\nThis is equivalent to previous GenericAquarium class declaration:"
        )
        Text(text = "class GenericAquarium<T: Any?>(val waterSupply: T)")

        MediumSpacer()
        Text(text = "Generic constraint", fontWeight = FontWeight.SemiBold)
        CardSubtitle(text = "To not allow passing null you can make T of type Any")
        Text(text = "class GenericAquarium<T: Any>(val waterSupply: T)")
        SmallSpacer()

        CardSubtitle(text = "To make sure that only a WaterSupply can be passed for T")
        Text(text = "class GenericAquarium<T: WaterSupply>(val waterSupply: T)")

        MediumSpacer()
        Text(text = "Add more checking", fontWeight = FontWeight.SemiBold)
        CardSubtitleOSS(text = "check() function is a standard library in Kotlin. It acts as an assertion and will throw an IllegalStateException if its argument evaluates to false")
        CardSubtitle(
            text = "class GenericAquarium<T: WaterSupply>(val waterSupply: T) {\n" +
                    "    fun addWater() {\n" +
                    "        check(!waterSupply.needsProcessing) { \"water supply needs processing first\" }\n" +
                    "        println(\"adding water from \$waterSupply\")\n" +
                    "    }\n" +
                    "}"
        )
        Text(
            text = "GenericAquarium(LakeWater()).addWater() -> java.lang.IllegalStateException: water supply needs processing first"    // ${GenericAquariumOnlyWaterSupply(LakeWater()).addWater()
        )
        CardSubtitleOSS(text = "Use the check() function to help ensure that your code is working as expected.")

    }
}

@Composable
fun GenericsScreen() {
    LazyColumn {
        item { ExploreGenerics() }
    }
}

@Preview
@Composable
fun GenericsScreenPrev() {
    LazyColumn {
        item { ExploreGenerics() }
    }
}