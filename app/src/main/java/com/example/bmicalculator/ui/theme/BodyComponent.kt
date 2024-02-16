package com.example.bmicalculator.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Body() {

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var classification by remember { mutableStateOf("") }
    var calculation by remember { mutableFloatStateOf(0.0f) }
    var progress by remember { mutableFloatStateOf(0.0f) }
    var classificationColor by remember {
        mutableStateOf(Color.White)
    }

    Column(
        Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = "BMI",
            color = Black,
            fontSize = 34.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "METRICS",
            color = Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        // Custom TextField Component
        TextFieldComponent(
            inputValue = height,
            metric = "M",
            label = "HEIGHT",
            onValueChange = {
                height = it
                if (height.isEmpty() || weight.isEmpty()) {
                    classification = ""
                    calculation = 0f
                    progress = 0f
                } else {
                    calculation = computeBMI(height, weight)
                    calculation = computeBMI(height, weight)
                    classification = classifyBMI(calculation)
                    progress = getProgress(calculation)
                    classificationColor = getClassificationColor(calculation)
                }
            }
        )
        TextFieldComponent(
            inputValue = weight,
            metric = "KG",
            label = "WEIGHT",
            onValueChange = {
                weight = it
                if (height.isEmpty() || weight.isEmpty()) {
                    classification = ""
                    calculation = 0f
                    progress = 0f
                } else {
                    calculation = computeBMI(height, weight)
                    classification = classifyBMI(calculation)
                    progress = getProgress(calculation)
                    classificationColor = getClassificationColor(calculation)
                }
            }
        )

        Box(Modifier.padding(vertical = 16.dp), contentAlignment = Center) {
            Text(
                text = classification,
                color = classificationColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
        }


        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 64.dp)
                .size(250.dp),
            contentAlignment = Center
        ) {
            Text(
                text = String.format("%.2f", calculation), // Format Result
                color = Black,
                fontSize = 64.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
            )
            CircularProgressIndicator(
                progress = progress,
                color = classificationColor,
                strokeWidth = 16.dp,
                modifier = Modifier.fillMaxSize(),
            )
        }

    }
}

// LOGIC FUNCTION TO CATEGORIZE WEIGHT COMPUTATION
fun classifyBMI(
    calculation: Float
): String {
    var classification = ""
    if (calculation < 18.5) {
        classification = "UNDERWEIGHT"
    } else if (calculation >= 18.5 && calculation < 24.9) {
        classification = "HEALTHY"
    } else if (calculation >= 24.9 && calculation < 30) {
        classification = "OVERWEIGHT"
    } else if (calculation >= 30) {
        classification = "OBESE"
    }
    return classification
}

fun getProgress(
    calculation: Float
): Float {
    var progress = 0f
    if (calculation < 18.5) {
        progress = .1f
    } else if (calculation >= 18.5 && calculation < 24.9) {
        progress = .5f
    } else if (calculation >= 24.9 && calculation < 30) {
        progress = .75f
    } else if (calculation >= 30) {
        progress = 1f
    }
    return progress
}

fun getClassificationColor(
    calculation: Float
): Color {
    var classificationColor = Color.Black
    if (calculation < 18.5) {
        classificationColor = Color.Black
    } else if (calculation >= 18.5 && calculation < 24.9) {
        classificationColor = Green
    } else if (calculation >= 24.9 && calculation < 30) {
        classificationColor = Yellow
    } else if (calculation >= 30) {
        classificationColor = Red
    }
    return classificationColor
}