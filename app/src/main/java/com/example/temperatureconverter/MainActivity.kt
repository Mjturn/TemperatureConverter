package com.example.temperatureconverter

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var fahrenheitToCelsius: RadioButton
    private lateinit var celsiusToFahrenheit: RadioButton
    private lateinit var tempInput: EditText
    private lateinit var tempConversion: TextView
    private var temp: Double = 0.0
    private lateinit var history: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fahrenheitToCelsius = findViewById(R.id.fahrenheitToCelsius)
        celsiusToFahrenheit = findViewById(R.id.celsiusToFahrenheit)
        tempInput = findViewById(R.id.tempInput)
        tempConversion = findViewById(R.id.tempConversion)
        history = findViewById(R.id.history)

        history.movementMethod = ScrollingMovementMethod.getInstance()

        if (savedInstanceState != null) {
            history.text = savedInstanceState.getString("textView")
            tempConversion.text = savedInstanceState.getString("result")
        }

    }

    fun convertTemp(view: View) {
        val tempInputToDouble = tempInput.text.toString().toDoubleOrNull() ?: 0.0
        val df = DecimalFormat("#.#")
        val formattedTempInput = df.format(tempInput.text.toString().toDoubleOrNull() ?: 0.0)

        if (fahrenheitToCelsius.isChecked) {
            temp = (tempInputToDouble - 32.0) * 5.0 / 9.0
            val formattedTemp = df.format(temp)
            tempConversion.text = formattedTemp.toString()
            history.append("$formattedTempInput degrees Fahrenheit -> $formattedTemp degrees Celsius\n\n")
        } else if (celsiusToFahrenheit.isChecked) {
            temp = (tempInputToDouble * 9.0 / 5.0) + 32.0
            val formattedTemp = df.format(temp)
            tempConversion.text = formattedTemp.toString()
            history.append("$formattedTempInput degrees Celsius -> $formattedTemp degrees Fahrenheit\n\n")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("textView", history.text.toString())
        outState.putString("result", tempConversion.text.toString())
        super.onSaveInstanceState(outState)
    }
}