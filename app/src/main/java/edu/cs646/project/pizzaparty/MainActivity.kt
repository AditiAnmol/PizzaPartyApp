package edu.cs646.project.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * constant slices per pizza
 */
const val SLICES_PER_PIZZA = 8

/**
 * Main class for Pizza Party App
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
        howHungrySelection = findViewById(R.id.hungryRadioGroup)
    }

    /**
     * Calculates pizza count for each input
     */
    fun calculatePizza(view: View) {
        val numAttendStr = numAttendees.text.toString()
        val numAttend = numAttendStr.toInt()

        val slicesPerPerson = when (howHungrySelection.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        val totalPizzas = ceil(numAttend * slicesPerPerson /
                SLICES_PER_PIZZA.toDouble()).toInt()
        totalPizzaCount.text = "Total pizzas: $totalPizzas"
    }
}