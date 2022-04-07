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

class GenericAquarium<T>(val waterSupply: T) {
    /**
     * Method with reified type
     *
     * Generic types are normally only available at compile time,
     * and get replaced with the actual types.
     * To keep a generic type available until run time,
     * declare the function inline and make the type reified.
     */
    inline fun <reified R : WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

/**
 * Extension functions
 *
 */
inline fun <reified T : WaterSupply> WaterSupply.isOfType() = this is T

/**
 * Star-projections
 */
inline fun <reified R : WaterSupply> GenericAquarium<*>.hasWaterSupplyOfTypeStar() =
    waterSupply is R


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
        Title(text = "Explore generics")
        Note(text = "A generic type allows you to make a class generic, and thereby make a class much more flexible")
        SmallSpacer()
        Code(
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
        Code(text = "val genAquarium = GenericAquarium(TapWater())")
        MediumSpacer()
        Text(text = "genAquarium.waterSupply.needsProcessing -> ${genAquarium.waterSupply.needsProcessing}")
        genAquarium.waterSupply.addChemicalCleaners()
        Note(text = "Because it is of type TapWater, you can call addChemicalCleaners() without any type casts")
        Text(text = "genAquarium.waterSupply.addChemicalCleaners()")
        Text(text = "genAquarium.waterSupply.needsProcessing -> ${genAquarium.waterSupply.needsProcessing}")

        Note(text = "But generic means you can pass almost anything")
        Text(text = "GenericAquarium(\"string\")")
        Text(text = "GenericAquarium(null)")
        Note(
            text = "Is possible by default beacause T stands for the nullable Any? type, that's the type at the top of the hierarchy." +
                    "\nThis is equivalent to previous GenericAquarium class declaration:"
        )
        Text(text = "class GenericAquarium<T: Any?>(val waterSupply: T)")

        MediumSpacer()
        Text(text = "Generic constraint", fontWeight = FontWeight.SemiBold)
        Note(text = "To not allow passing null you can make T of type Any")
        Text(text = "class GenericAquarium<T: Any>(val waterSupply: T)")
        SmallSpacer()

        Note(text = "To make sure that only a WaterSupply can be passed for T")
        Text(text = "class GenericAquarium<T: WaterSupply>(val waterSupply: T)")

        MediumSpacer()
        Text(text = "Add more checking", fontWeight = FontWeight.SemiBold)
        Note(text = "check() function is a standard library in Kotlin. It acts as an assertion and will throw an IllegalStateException if its argument evaluates to false")
        Code(
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
        Note(text = "Use the check() function to help ensure that your code is working as expected.")
    }
}


/**
 * In out types
 */
class AquariumOut<out T : WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) = waterSupply.addChemicalCleaners()
}

interface Cleaner<in T : WaterSupply> {
    fun clean(waterSupply: T)
}

fun addItemTo(aquarium: AquariumOut<WaterSupply>) = println("item added")

val aquariumOut = AquariumOut(TapWater())
val cleaner = TapWaterCleaner()

@Composable
fun InOutTypes() {
    CardContainer {
        Title(text = "In and out types")
        Note(text = "An in type is a type that can only be passed into a class, not returned. An out type is a type that can only be returned from a class")
        SmallSpacer()
        TextSemibold(text = "Out type")
        Code(text = "class AquariumOut<out T : WaterSupply>(val waterSupply: T) {...}")
        SmallSpacer()
        TextSemibold(text = "In type")
        Code(text = "interface Cleaner<in T : WaterSupply> {...}")
    }
}


/**
 * Generic functions
 * Typically, making a generic function is a good idea whenever the function takes an argument of a class that has a generic type
 */
fun <T : WaterSupply> isWaterClean(aquarium: GenericAquarium<T>): String {
    return "aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}"
}

@Composable
fun GenericFunctions() {
    CardContainer {
        Title(text = "Generic functions")
        Note(text = "making a generic function is a good idea whenever the function takes an argument of a class that has a generic type")
        SmallSpacer()
        Code(
            text = "fun <T : WaterSupply> isWaterClean(aquarium: GenericAquarium<T>): String {\n" +
                    "    return \"aquarium water is clean: \${!aquarium.waterSupply.needsProcessing}\"\n" +
                    "}"
        )
        SmallSpacer()
        Text(text = "isWaterClean(GenericAquarium(TapWater())) -> ${isWaterClean(genAquarium)}")

    }
}

@Composable
fun GenericMethodWithReifiedType() {
    CardContainer {
        Title(text = "Method with a reified type")
        Code(
            text = "class GenericAquarium<T>(val waterSupply: T) {\n" +
                    "    inline fun <reified R : WaterSupply> hasWaterSupplyOfType() = waterSupply is R\n" +
                    "}"
        )
        Note(
            text = "To do is check you need to tell Kotlin that this type is reified and it can be used in the function." +
                    "For this you can put inline in front of fun keyword"
        )
        Text(text = "genAquarium.hasWaterSupplyOfType<TapWater>() -> ${genAquarium.hasWaterSupplyOfType<TapWater>()}")
    }
}

@Composable
fun ExtensionFunctions() {
    CardContainer {
        Title(text = "Extension functions")
        Note(text = "Like methods you can use reified types for regular and etension functions too")
        Code(text = "inline fun <reified T : WaterSupply> WaterSupply.isOfType() = this is T")
        Note(text = "genAquarium.waterSupply.isOfType<TapWater>() -> ${genAquarium.waterSupply.isOfType<TapWater>()}")

        MediumSpacer()
        TextSemibold(text = "Star-projections")
        Note(text = "TowerTank is an Aquarium sublcass")
        Code(text = "open class Aquarium(var width: Int = 20, open var height: Int = 40, var length: Int = 100) {...}")
        Code(
            text = "class TowerTank(override var height: Int, var diameter: Int) :\n" +
                    "    Aquarium(height = height, width = diameter, length = diameter) {...}"
        )
        SmallSpacer()
        Code(
            text = "inline fun <reified R : WaterSupply> GenericAquarium<*>.hasWaterSupplyOfType() =\n" +
                    "        waterSupply is R"
        )
        Text(text = "genAquarium.hasWaterSupplyOfTypeStar<TapWater>() -> ${genAquarium.hasWaterSupplyOfTypeStar<TapWater>()}")
        Note(
            text = "Doesn't matter what type of Aquarium it is. Using the star-projection syntax is a convenient way to specify a variety of matches. " +
                    "And when you use a star-projection, Kotlin will make sure you don't do anything unsafe, too"
        )
    }
}

@Composable
fun GenericsScreen() {
    LazyColumn {
        item { ExploreGenerics() }
        item { InOutTypes() }
        item { GenericFunctions() }
        item { GenericMethodWithReifiedType() }
        item { ExtensionFunctions() }
    }
}

@Preview
@Composable
fun GenericsScreenPrev() {
    LazyColumn {
//        item { ExploreGenerics() }
//        item { InOutTypes() }
//        item { GenericFunctions() }
        item { GenericMethodWithReifiedType() }
        item { ExtensionFunctions() }
    }
}