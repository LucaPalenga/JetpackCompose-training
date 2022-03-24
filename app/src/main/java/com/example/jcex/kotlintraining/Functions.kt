package com.example.jcex.kotlintraining

val week = arrayOf(
    "Monday", "Tuesday", "Wednesday", "Thursday",
    "Friday", "Saturday", "Sunday"
)

/**
 * When expression
 */
fun fishFood(day: String): String {
    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitos"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "nothing"
    }
}

fun feedFish() {
    println("today eat ${fishFood(week.random())}")
}

/**
 * Compact function
 */
fun isSunday(day: String) = day == "Sunday"
fun isHot(temperature: Int) = temperature > 30
fun isDirty(dirty: Int) = dirty > 30

// best practice: put parameters with default values after the ones without values
fun shouldChangeFishWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

/**
 * Filters
 */
val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

val eagerFilter = decorations.filter { it[0] == 'p' }   //immediatelly create a new list

// using Sequence filter is applied only when you access elements
// (lazyFilter is not a list, to access list use .toList())
val lazyFilter = decorations.asSequence().filter { it[0] == 'p' }


/**
 * Map
 * The map() function performs a simple transformation on each element in the sequence
 */
val map = decorations.map { println("access: $it") }

/**
 * Lambdas
 */
val waterFilter: (Int) -> (Int) = { dirty -> dirty / 2 }

//high-order function
fun updateDirty(dirty: Int, operation: (Int) -> (Int)): Int {
    return operation(dirty)
}
//test println(updateDirty(30, waterFilter))

// :: specifies than i'm passing a regular function
fun increaseDirty(start: Int) = start + 1
// updateDirty(15, ::increaseDirty)
