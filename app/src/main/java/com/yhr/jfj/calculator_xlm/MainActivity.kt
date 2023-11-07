package com.yhr.jfj.calculator_xlm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Show digit
    fun onDigit(view: View){
        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show()
    }
}