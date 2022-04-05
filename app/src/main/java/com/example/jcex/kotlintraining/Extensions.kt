package com.example.jcex.kotlintraining

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
val numbers = Triple(6,9,42)
//numbers.toList()  ⇒   [6, 9, 42]

/**
 * Destructure of pairs and triples
 */
//val (tool,use) = equipment
//val (n1, n2, n3) = numbers


