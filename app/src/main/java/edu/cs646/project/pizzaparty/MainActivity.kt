package edu.cs646.project.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * Controller class for Pizza Party App
 */
class MainActivity : AppCompatActivity() {

    /**
     * numAttendees - holds the number of people attending the party
     */
    private lateinit var numAttendees: EditText
    /**
     * totalPizzaCount - holds the calculated number of pizzas
     */
    private lateinit var totalPizzaCount: TextView
    /**
     * howHungrySelection - holds the hunger selection from radio buttons group
     */
    private lateinit var howHungrySelection: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendees = findViewById(R.id.peopleCount)
        totalPizzaCount = findViewById(R.id.totalPizza)
        val totalText = getString(R.string.total_pizzas, 0)
        totalPizzaCount.text = totalText
        howHungrySelection = findViewById(R.id.hungryRadioGroup)
    }

    /**
     * Calculates pizza count for each input
     */
    fun calculatePizza(view: View) {
        // Get the text that was typed into the EditText
        val numAttendStr = numAttendees.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungrySelection.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        totalPizzaCount.text = totalText
    }
}