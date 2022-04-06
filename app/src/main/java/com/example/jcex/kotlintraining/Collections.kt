package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CollectionsScreen() {
    /**
     * Lista immutabile
     */
    val immutableList = listOf("pippo", "pluto", "paperino")

    /**
     * Lista mutabile
     */
    val mutableList = mutableListOf(1, 2, 3, 4)

    /**
     * Arrays
     */
    val array1 = arrayOf("pippo", 1, 2.0)
    val array2 = intArrayOf(2, 3)

    /**
     * Hash map
     */
    val hashMap = hashMapOf(1 to "A", 2 to "B", 3 to "C")

    /**
     * Mutable map
     */
    val mutableMap = mutableMapOf(1 to "ciao")

    /**
     * Pair
     */
    val equipment = "fish net" to "catching fish"
    val equipment2 = ("fish net" to "catching fish") to "equipment"
    //equipment2.first is equipment2.second   ⇒   (fish net, catching fish) is equipment
    //equipment2.first.second   ⇒   catching fish

    /**
     * Triple
     */
    val numbers = Triple(6, 9, 42)
    //numbers.toList()  ⇒   [6, 9, 42]

    /**
     * Destructure of pairs and triples
     */
    //val (tool,use) = equipment
    val (n1, n2, n3) = numbers


    LazyColumn() {
        item {
            CardContainer {
                CardTitle("Lista immutabile")
                CardSubtitle(text = "listOf(\"pippo\", \"pluto\", \"paperino\")")
                ListContainer(list = immutableList)
            }
        }
        item {
            CardContainer {
                CardTitle("Lista mutabile")
                CardSubtitle("mutableListOf(1, 2, 3, 4)")
                CardSubtitle("mutableList.remove(1) = ${mutableList.remove(1)}")
                ListContainer(mutableList)
                Text(text = "list.sum() -> ${mutableList.sum()}")
            }
        }
        item {
            CardContainer {
                CardTitle("Array (sempre immutabile)")
                CardSubtitle(text = "arrayOf(\"pippo\", 1, 2.0)")
                ArrayContainer(array1 as Array<Any>)
            }
        }
        item {
            CardContainer {
                CardTitle(text = "Pair")
                CardSubtitle(
                    text = "val equipment = \"fish net\" to \"catching fish\"\n" +
                            "val equipment2 = (\"fish net\" to \"catching fish\") to \"equipment\""
                )
                Text(text = "equipment.first -> ${equipment.first}")
                Text(text = "equipment2.first.second -> ${equipment2.first.second}")
            }
        }
        item {
            CardContainer {
                CardTitle(text = "Triple")
                CardSubtitle(
                    text = "val numbers = Triple(6, 9, 42)"
                )
                Text(text = "numbers.toList() -> ${numbers.toList()}")
                CardSubtitle(text = "Destructure of pair and triple -> val (n1, n2, n3) = numbers")
                Text(text = "n1 = $n1, n2 = $n2, n3 = $n3")
            }
        }
        item {
            CardContainer {
                CardTitle("HashMap - hashMapOf")
                CardSubtitle(text = "hashMapOf(1 to \"A\", 2 to \"B\", 3 to \"C\")")
                Text(text = "hashMap.get(1) -> ${hashMap.get(1)}")
                Text(text = "hashMap[1] -> ${hashMap[1]}")
                Text(text = "hashMap[4]] -> ${hashMap[4]}")
                Text(
                    text = "hashMap.getOrDefault(4,\"default value\") -> ${
                        hashMap.getOrDefault(
                            4,
                            "default value"
                        )
                    }"
                )
                Text(text = "hashMap.getOrElse(4){\"else scope...\"} -> ${hashMap.getOrElse(4) { "else scope..." }}")
            }
        }
        item {
            CardContainer {
                CardTitle("Mutable map - mutableMapOf")
                CardSubtitle(text = "mutableMapOf(1 to \"ciao\")")
                Text(text = "mutableMap.put(2,\"hello\") -> ${mutableMap.put(2, "hello")}")
                Text(text = "mutableMap = $mutableMap")
                Text(text = "mutableMap.remove(1) -> ${mutableMap.remove(1)}")
                Text(text = "mutableMap = $mutableMap")
            }
        }
    }
}

@Preview
@Composable
fun CollectionsPreview() {
    CollectionsScreen()
}