package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Collections() {
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

    LazyColumn() {
        item {
            CardContainer {
                CardTitle("Lista immutabile - listOf")
                ListContainer(list = immutableList)
            }
        }
        item {
            CardContainer {
                CardTitle("Lista mutabile - mutableListOf")
                CardSubtitle("Remove element 1 = ${mutableList.remove(1)}")
                ListContainer(mutableList)
                Text(text = ".sum() -> ${mutableList.sum()}")
            }
        }
        item {
            CardContainer {
                CardTitle("Array (sempre immutabile) - arrayOf")
                ArrayContainer(array1 as Array<Any>)
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
    Collections()
}