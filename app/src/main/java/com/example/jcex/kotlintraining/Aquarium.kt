package com.example.jcex.kotlintraining

//constructor is in the class declaration
class Aquarium(var width: Int = 20, var height: Int = 40, var length: Int = 100) {

    init {
        //if you need more initialization operations you can write it here
        println("initializing")
    }
}