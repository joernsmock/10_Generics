abstract class Pet(var name: String) {
    abstract fun noise()
}

// needs to be open if you want to subclass it
open class Cat(name: String) : Pet(name) {
    override fun noise() {
        println("meow!")
    }
}

open class AegeanCat(name: String): Cat(name) {
    override fun noise() {
        println("meow!")
    }
}

class DeadAegeanCat(name: String): AegeanCat(name) {
    override fun noise() {
        println("+")
    }
}

class Dog(name: String): Pet(name) {
    override fun noise() {
        println("wuff!")
    }
}

class Fish(name: String): Pet(name) {
    override fun noise() {
        println("crck!")
    }
}

interface Retailer<out T> {
    fun sell(): T
}

class CatRetailer : Retailer<Cat> {
    override fun sell(): Cat {
        println("CatRetailer sells you a cat.")
        return Cat ("") // create and return a cat object
    }
}

class Contest<T: Pet> {
    val scores: MutableMap<T, Int> = mutableMapOf()
    fun addScore(t: T, score: Int = 0) {
        if (score > 0) scores.put(t, score)
    }
    fun getWinners(): MutableSet<T> {
        val winners: MutableSet<T> = mutableSetOf()

        val highScore = scores.values.max() // max() is nullable (changed?)
        // (shown by
        // stroked font in IDEA)
        // automatic formatting (live) and reformat-All is just plain fun
        for ((t, score) in scores) {
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}

tailrec fun fact(n: Int, acc: Int = 1): Int {
    if (n == 1) return acc
    return fact(n - 1, acc * n)
}

fun main(args: Array<String>) {
    val f = fact(5,1)
    println("f = "+f)
    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Fish Finny")

    val catContest = Contest<Cat> ()
    catContest.addScore(catFuzz, 50)
    catContest.addScore(catKatsu, 45)
    val topCat = catContest.getWinners().first()
    println("Cat contest winner is ${topCat.name}")

    val petContest = Contest<Pet> ()
    petContest.addScore(catFuzz, 50)
    petContest.addScore(fishFinny, 55)
    val topPet = petContest.getWinners().first()
    println("Pet contest winner is ${topPet.name}")

    val catRetailer: Retailer<Cat> = CatRetailer()
    val petRetailer: Retailer<Pet> = CatRetailer()

    // sell returns an object that may just vanish?
    petRetailer.sell()
    // ...I can put the return value into a variable:
    val newPet = petRetailer.sell()
    newPet.noise()
}