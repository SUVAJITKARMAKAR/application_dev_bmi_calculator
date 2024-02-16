package com.example.bmicalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    inputValue: String,
    metric: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = inputValue,
            onValueChange = onValueChange,
            label = { Text(label, color = Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = someWhatTeal,
                unfocusedIndicatorColor = Color.Black,
            ),
        )
        Text(
            text = metric,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Black
        )
    }
}




// BMI_MEASUREMENT_LOGIC
fun computeBMI(height: String, weight: String): Float {

    val calculation: Float
    val w = weight.toFloat()
    val h = height.toFloat()
    calculation = (w / h.pow(2))
    return calculation
}