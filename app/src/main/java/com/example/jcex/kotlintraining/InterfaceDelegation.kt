package com.example.jcex.kotlintraining

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


private interface FishAction2 {
    fun eat(): String
}

interface FishColor {
    val color: String
}

/**
 * Singleton class - by object keywork
 * Kotlin only creates one instance of this class
 */
object GoldColor : FishColor {
    override val color = "gold"
}

/**
 * Interface delegation
 * Instead implementing FishColor use implementation provided by GoldColor
 * so every time color is accessed, it's delegate to GoldColor
 */
class Plecostomus2 : FishColor by GoldColor, FishAction2 {
    override fun eat(): String {
//        println("eat algae")
        return "eat algae"
    }
}

/**
 * Interface delegation (2)
 * by adding contructor parameter (fishColor) you can set GoldColor as default but let
 * Plecostomus3 to have other colors. Change also "by GoldColor" to "by fishColor" cause
 * now it's parametrized
 */
class Plecostomus3(fishColor: FishColor = GoldColor) : FishColor by fishColor, FishAction2 {
    override fun eat(): String {
//        println("eat algae")
        return "eat algae"
    }
}

/**
 * Interface delegation (3)
 * in the same way create a class how implements FishAction2
 * and delegate the Plecostomus4's FishAction2 to PrintFishAction using by
 */
class PrintFishAction(val food: String) : FishAction2 {
    override fun eat(): String {
        return food
//        println(food)
    }
}

class Plecostomus4(fishColor: FishColor = GoldColor) : FishColor by fishColor,
    FishAction2 by PrintFishAction("eat algae")


//appling interface delegation to shark2
object GrayColor : FishColor {
    override val color = "gray"
}

class Shark2 : FishColor by GrayColor, FishAction2 by PrintFishAction("hunt and ead fish")

@Composable
fun InterfaceDelegation() {
    CardContainer {
        Title(text = "InterfaceDelegation")
        Code(
            text = "object GoldColor : FishColor {\n" +
                    "    override val color = \"gold\"\n" +
                    "}"
        )
        SmallSpacer()
        Code(
            text = "class PrintFishAction(val food: String) : FishAction {\n" +
                    "    override fun eat(): String {\n" +
                    "        return food\n" +
                    "    }\n" +
                    "}"
        )
        SmallSpacer()
        Code(
            text = "class Plecostomus(fishColor: FishColor = GoldColor) : FishColor by fishColor" +
                    "FishAction by PrintFishAction(\"eat algae\")"
        )
        SmallSpacer()
        Note(
            text = "This class is delegating FishAction implementation to PrintFishAction and" +
                    "FishColor to GoldColor (this one is parametrized so GoldColor is default," +
                    "but it can be modified - for ex. Plecostomus(GrayColor)"
        )
        SmallSpacer()
        Text(text = "Plecostomus().color -> ${Plecostomus4().color}")
        Text(text = "Plecostomus(GrayColor).color -> ${Plecostomus4(GrayColor).color}")
        Text(text = "Plecostomus().eat() -> ${Plecostomus4().eat()}\")")
//        Text(text = "Shark().color -> ${Shark2().color}")
//        Text(text = "Shark().eat() -> ${Shark2().eat()}")
    }
}

@Preview
@Composable
fun InterfaceDelegationPrev() {
    InterfaceDelegation()
}