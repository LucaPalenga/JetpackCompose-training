package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

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


@Composable
fun WhenExpression(weekDay: String) {
    CardContainer {
        Title(text = "When expression")
        Code(
            text = "fun fishFood(day: String): String {\n" +
                    "    return when (day) {\n" +
                    "        \"Monday\" -> \"flakes\"\n" +
                    "        \"Tuesday\" -> \"pellets\"\n" +
                    "        \"Wednesday\" -> \"redworms\"\n" +
                    "        \"Thursday\" -> \"granules\"\n" +
                    "        \"Friday\" -> \"mosquitos\"\n" +
                    "        \"Saturday\" -> \"lettuce\"\n" +
                    "        \"Sunday\" -> \"plankton\"\n" +
                    "        else -> \"nothing\"\n" +
                    "    }\n" +
                    "}"
        )
        Text(text = "fishFood($weekDay) -> ${fishFood(weekDay)}")
    }
}

@Composable
fun CompactFunction(weekDay: String) {
    CardContainer {
        Title(text = "Compact function")
        Code(
            text = "fun isSunday(day: String) = day == \"Sunday\"\n" +
                    "fun isHot(temperature: Int) = temperature > 30\n" +
                    "fun isDirty(dirty: Int) = dirty > 30"
        )
        SmallSpacer()
        Code(
            text = "fun shouldChangeFishWater(day: String, temperature: Int = 22, dirty: Int = 20): Boolean {\n" +
                    "    return when {\n" +
                    "        isHot(temperature) -> true\n" +
                    "        isDirty(dirty) -> true\n" +
                    "        isSunday(day) -> true\n" +
                    "        else -> false\n" +
                    "    }\n" +
                    "}"
        )
        Note(text = "best practice: put parameters with default values after the ones without values")
        SmallSpacer()
        Text(text = "shouldChangeFishWater($weekDay) -> ${shouldChangeFishWater(weekDay)}")
    }
}

@Composable
fun FilterFunction() {
    CardContainer {
        Title(text = "Filter")
        Code(text = "val decorations = listOf(\"rock\", \"pagoda\", \"plastic plant\", \"alligator\", \"flowerpot\")")
        SmallSpacer()
        Text(
            text = "Eager filter - immediatelly create a new list",
            fontWeight = FontWeight.SemiBold
        )
        Text(text = "decorations.filter { it[0] == 'p' } -> ${decorations.filter { it[0] == 'p' }}")
        Text(
            text = "Lazy filter - using Sequence filter is applied only when you access elements",
            fontWeight = FontWeight.SemiBold
        )
        SmallSpacer()
        Text(
            text = "decorations.asSequence().filter { it[0] == 'p' } -> ${
                decorations.asSequence().filter { it[0] == 'p' }
            }"
        )
        Note(text = "lazyFilter is not a list, to access list use .toList()")
        Text(
            text = "decorations.asSequence().filter { it[0] == 'p' }.toList() -> ${
                decorations.asSequence().filter { it[0] == 'p' }.toList()
            }"
        )
    }
}

@Composable
fun MapFunction() {
    CardContainer {
        Title(text = "Map")
        Code(text = "val map = decorations.map { println(\"access: \$it\") }")
        Text(text = "map -> $map")
    }
}

@Composable
fun LambdaFunction() {
    CardContainer {
        Title(text = "Lambdas")
        Code(text = "val waterFilter: (Int) -> (Int) = { dirty -> dirty / 2 }")
        Code(
            text = "fun updateDirty(dirty: Int, operation: (Int) -> (Int)): Int {\n" +
                    "    return operation(dirty)\n" +
                    "}"
        )
        Text(text = "updateDirty(30, waterFilter) -> ${updateDirty(30, waterFilter)}")
        SmallSpacer()

        Code(text = "fun increaseDirty(start: Int) = start + 1")
        Text(text = "updateDirty(15, ::increaseDirty) -> ${updateDirty(15, ::increaseDirty)}")
        Note(text = ":: specifies than i'm passing a regular function")
    }
}

@Composable
fun FunctionsScreen() {
    val weekDay = week.random()

    LazyColumn {
        item { WhenExpression(weekDay = weekDay) }
        item { CompactFunction(weekDay = weekDay) }
        item { FilterFunction() }
        item { MapFunction() }
        item { LambdaFunction() }
    }
}

@Preview
@Composable
fun FunctionsScreenPrev() {
    FunctionsScreen()
}
