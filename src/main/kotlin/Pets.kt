abstract class Pet(var name: String)
open class Cat(name: String) : Pet(name) // needs to be if you want to subclass it
class AegeanCat(name: String): Cat(name)
class Dog(name: String): Pet(name)
class Fish(name: String): Pet(name)

interface Retailer<out T> {
    fun sell(): T
}

class CatRetailer : Retailer<Cat> {
    override fun sell(): AegeanCat {
        println("sell a cat")
        return AegeanCat ("") // create a new cat object
    }
}

class Contest<T: Pet> {
    val scores: MutableMap<T, Int> = mutableMapOf()
    fun addScore(t: T, score: Int = 0) {
        if (score > 0) scores.put(t, score)
    }
    fun getWinners(): MutableSet<T> {
        val winners: MutableSet<T> = mutableSetOf()

        val highScore = scores.values.maxOrNull() // 'max()' is deprecated (shown by stroked font in IDEA)
        // automatic formatting (live) and reformat-All is just plain fun
        for ((t, score) in scores) {
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}

fun main(args: Array<String>) {
    val catFuzz = Cat("Fuzz Lightyear")
    val catKatsu = Cat("Katsu")
    val fishFinny = Fish("Finny McGraw")

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
    petRetailer.sell()
}