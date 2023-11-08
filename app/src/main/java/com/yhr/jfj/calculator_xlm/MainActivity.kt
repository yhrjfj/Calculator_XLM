package com.yhr.jfj.calculator_xlm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    // Use this for checking if user use dot to a previous number
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    // Show digit
    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    // Clear screen
    fun onClear(view: View) {
        tvInput?.text = ""
    }

    // Remove last digit from input screen
    fun onAc(view: View) {
        val value = tvInput?.text
        if (value?.isNotEmpty() == true) {
            tvInput?.text = value.substring(0, value.length - 1)
        }
    }


    // Decimal point
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    // Operator methods show. When user use one of the given operator he or she can not use other operator expect hit equal button
    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    // Remove zero after doing some calculation which is not a floating number
    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    // onEqual method, It works when user hit equal button
    @SuppressLint("SetTextI18n")
    fun onEqual(view: View) {
        var tvValue = tvInput?.text.toString()
        var prefix = ""
        try {
            // If the number is negative, then we will remove the negative sign and add it later
            if (tvValue.startsWith("-")) {
                prefix = "-"
                // Remove negative sign from the number. If the user gives -99, then we will remove the - sign and get 99 because we start from the 1st index
                tvValue = tvValue.substring(1) // Update tvValue here
            }

            if (tvValue.contains("-")) {
                // Minus operator

                val splitValue = tvValue.split("-")

                // Here we split the given number into two parts like: 99 - 1, which will be split into two parts, 99 and 1
                var one = splitValue[0] // 99
                val two = splitValue[1] // 1

                // Though we remove the negative sign from the number, we will add that sign again
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                // View result for minus
                tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

            } else if (tvValue.contains("+")) {
                // Plus operator

                val splitValue = tvValue.split("+")

                // Here we split the given number into two parts like: 99 - 1, which will be split into two parts, 99 and 1
                var one = splitValue[0] // 99
                val two = splitValue[1] // 1

                // Though we remove the negative sign from the number, we will add that sign again
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                // View result for plus
                tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

            } else if (tvValue.contains("*")) {
                // Multiply operator

                val splitValue = tvValue.split("*")

                // Here we split the given number into two parts like: 99 - 1, which will be split into two parts, 99 and 1
                var one = splitValue[0] // 99
                val two = splitValue[1] // 1

                // Though we remove the negative sign from the number, we will add that sign again
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                // View result for multiply
                tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

            } else if (tvValue.contains("/")) {
                // Deviation operator

                val splitValue = tvValue.split("/")

                // Here we split the given number into two parts like: 99 - 1, which will be split into two parts, 99 and 1
                var one = splitValue[0] // 99
                val two = splitValue[1] // 1

                // Though we remove the negative sign from the number, we will add that sign again
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                // View result for deviation
                tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

            }

        } catch (e: ArithmeticException) {
            e.printStackTrace()
        }
    }
}