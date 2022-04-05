package com.example.jcex.kotlintraining

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
        CardTitle(text = "InterfaceDelegation")
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Plecostomus - color: ${Plecostomus4().color}, eat(): ${Plecostomus4().eat()}"
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Shark - color: ${Shark2().color}, eat(): ${Shark2().eat()}"
        )
    }
}

@Preview
@Composable
fun InterfaceDelegationPrev() {
    InterfaceDelegation()
}