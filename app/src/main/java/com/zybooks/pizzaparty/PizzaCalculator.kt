package com.zybooks.pizzaparty

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

/**
 * PizzaCalculator class calculates the number of pizzas required for the people
 * @setter partySize sets number of people attending the party
   and ensures the number is not negative.
 * @setter HungerLevel sets the hunger level.
 * @getter totalPizzas calculates the number of pizzas needed.
*/

class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {
    var partySize = 0
        set(value) {
            field = if (value >= 0) value else 0
        }

    enum class HungerLevel(var numSlices: Int) {
        LIGHT(2), MEDIUM(3), RAVENOUS(4)
    }

    val totalPizzas: Int
        get() {
            return ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
        }

    init {
        this.partySize = partySize
    }
}