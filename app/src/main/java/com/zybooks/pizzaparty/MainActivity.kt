package com.zybooks.pizzaparty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"
/** MainActivity class has the application logic which is controller of the app
 */
class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate was called")
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
//        numAttendEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s != null && s.isNotEmpty()) {
//                    numAttendEditText.setText("")
//                }
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }

    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        displayTotal()
    }
        // Place totalPizzas into the string resource and display
        private fun displayTotal() {
            val totalText = getString(R.string.total_pizzas,totalPizzas)
            numPizzasTextView.text = totalText
        }
}