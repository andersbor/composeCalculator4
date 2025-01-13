package com.example.calculator4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator4.ui.theme.Calculator4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        var number1Str by remember { mutableStateOf("0") }
        var number2Str by remember { mutableStateOf("0") }
        var result by remember { mutableStateOf("") }
        var fff by remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = number1Str,
            onValueChange = { number1Str = it },
            label = { Text("Enter number 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = number2Str,
            onValueChange = { number2Str = it },
            label = { Text("Enter number 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                enabled = number1Str.isNotEmpty() && number2Str.isNotEmpty(),
                onClick = {
                    result = (number1Str.toDouble() + number2Str.toDouble()).toString()
                }) {
                Text("+")
            }
            Button(
                enabled = number1Str.isDecimalNumber() && number2Str.isDecimalNumber(),
                onClick = {
                    result = (number1Str.toDouble() * number2Str.toDouble()).toString()
                }) {
                Text("*")
            }
            Button(
                enabled = number1Str.isDecimalNumber() && number2Str.isDecimalNumber(),
                onClick = {
                    result = (number1Str.toDouble() / number2Str.toDouble()).toString()
                }) {
                Text("/")
            }
            Button(
                enabled = number1Str.isDecimalNumber() && number2Str.isDecimalNumber(),
                onClick = {
                    result = (number1Str.toDouble() - number2Str.toDouble()).toString()
                }) {
                Text("-")
            }
        }
        if (!number1Str.isDecimalNumber()) {
            Text(color = Color.Red, text = "Number 1 is not a valid number")
        } else if (!number2Str.isDecimalNumber()) {
            Text(color = Color.Red, text = "Number 2 is not a valid number")
        } else if (result.isNotEmpty())
            Text(result)
    }
}

fun String.isDecimalNumber(): Boolean { // extension function: An extra method for class String
    try {
        this.toDouble()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
    //return this.matches(Regex("^\\-?[0-9]+(\\.[0-9]+)?$"))
}

fun isDouble(number: String): Boolean { // ordinary function
    try {
        number.toDouble()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator4Theme {
        Calculator()
    }
}