package com.example.jcex.kotlintraining

import java.lang.Math.PI


/**
 * INHERITANCE
 * You must mark a class as open to allow it to be subclassed.
 * Similarly, you must mark properties and member variables as open, in order to override them in the subclass
 */

//constructor is in the class declaration
private open class Aquarium(var width: Int = 20, open var height: Int = 40, var length: Int = 100) {

    open var volume: Int
        get() = width * height * length / 1000      //explicit property getter
        set(value) {                                //property setter
            height = (value * 1000) / (width * length)
        }

    open val shape = "rectangle"
    open val water: Double
        get() = volume * 0.9

    /**
     * VISIBILITY MODIFIERS
     * public - visible outside the class. Everything is public by default, including variables and methods of the class.
     * internal - only be visible within that module. A module is a set of Kotlin files compiled together, for example, a library or application.
     * private - only be visible in that class (or source file if you are working with functions).
     * protected is the same as private, but it will also be visible to any subclasses.
     */

    init {
        //if you need more initialization operations you can write it here
        println("initializing")
    }

    init {
        //init blocks are executed in the order in which they appear in the class definition
        println("second init")
    }

    /**
     * secondary constructor
     * every secondary constructor must call the primary - this() -
     * directly or indirectly (by another secondary that calls primary)
     */
    constructor(numberOfFish: Int) : this() {
        val tank = numberOfFish * 2000 * 1.1
    }

    fun buildAquarium() {
        val aquarium1 = Aquarium(width = 25)  //primary constructor
        val aquarium2 = Aquarium(height = 35, length = 110)  //primary constructor
        val aquarium3 = Aquarium(width = 25, height = 35, length = 110)  //primary constructor
        val aquarium4 = Aquarium(numberOfFish = 29)  //secondary constructor
    }
}

/**
 * Aquarium subclass
 */
private class TowerTank(override var height: Int, var diameter: Int) :
    Aquarium(height = height, width = diameter, length = diameter) {

    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width / 2 * length / 2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width / 2 * length / 2)).toInt()
        }
    override var water: Double = volume * 0.8
    override val shape: String = "cylinder"
}

/**
 * ABSTRACT class
 * Abstract classes are always open, you don't need to mark them with open.
 * Properties and methods of an abstract class are non-abstract unless you explicitly mark them with the abstract keyword.
 */
private abstract class AquariumFish : FishAction {
    abstract val color: String
    override fun eat() = println("yum")
}

/**
 * INTERFACE
 */
private interface FishAction {
    fun eat()

    //    fun jump()
    //    fun clean()
    //    fun catchFish()
    fun swim() {
        println("swim")
    }
}

/**
 * NB: Use an INTERFACE when you have a lot of methods and one or two implementations
 * NB: Use and ABSTRACT class any time you can't complete a class. When you can provide
 * most of methods/properties and let abstract just one or two properties/methods
 */

//subclasses
private class Shark : AquariumFish(), FishAction {
    override val color: String = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

private class Plecostomus : AquariumFish(), FishAction {
    override val color: String = "gold"
    override fun eat() {
        println("eat algae")
    }
}

//fun makeFish() {
//    val shark = Shark()
//    val plecostomus = Plecostomus2()
//
//    println("Shark color = ${shark.color}")
//    shark.eat()
//    shark.swim()
//
//    println("Plecostomus color = ${plecostomus.color}")
//    plecostomus.eat()
//    plecostomus.swim()
//}


//fun main() {
//    makeFish()
//}