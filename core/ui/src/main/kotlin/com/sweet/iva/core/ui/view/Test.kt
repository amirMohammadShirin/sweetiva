package com.sweet.iva.core.ui.view

interface Mouth {
    fun speak(word: String)
    fun eat(food: String)
}


class CatMouth : Mouth {
    override fun speak(word: String) {
        println(word)
    }

    override fun eat(food: String) {
        println("the cat is eating $food")
    }
}

class Cat : Mouth by CatMouth() {

    fun normalCat() {
        println("Normal cat")
    }

}


fun main() {

    val normalCat = Cat()
    normalCat.speak("meow")
    normalCat.eat("fish")
}