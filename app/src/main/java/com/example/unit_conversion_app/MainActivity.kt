package com.example.unit_conversion_app

import android.os.Bundle
import androidx.compose.ui.Alignment

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import com.example.unit_conversion_app.ui.theme.UnitConversionAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConversionAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor(){
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.End) {
        Button({}) {
            Icon(Icons.Default.AccountCircle,"Account Options")
        }
        Button({}) {
            Icon(Icons.Default.Menu,"Menu")
        }
    }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor = remember { mutableStateOf(1.00) }
    fun convertingUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble*conversionFactor.value*100/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally ) {
        Text("Unit Convertor", color = Color.Green, style =  MaterialTheme.typography.displayLarge)
        Spacer(Modifier.padding((20.dp)))
        OutlinedTextField(inputValue,{inputValue = it
            convertingUnit()}, label = { Text("Enter Value") })
        Spacer(Modifier.padding((20.dp)))
        Row {
            Box{
                Button(onClick = {iExpanded = true},) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, "Dropdown Menu")
                }
                DropdownMenu(iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = {Text("Meter")}, {
                        iExpanded = false
                        inputUnit = "Meter"
                        conversionFactor.value = 1.0
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Centimeter")}, {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        conversionFactor.value = 0.01
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Milimeters")}, {
                        iExpanded = false
                        inputUnit = "Milimeters"
                        conversionFactor.value = 0.001
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Feet")}, {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertingUnit()
                    })
                }

            }
            Spacer(Modifier.padding((20.dp)))
            Box{
                Button({oExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, "Dropdown Menu")
                }
                DropdownMenu(oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = {Text("Meter")}, {
                        oExpanded = false
                        outputUnit = "Meter"
                        oconversionFactor.value = 1.0
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Centimeter")}, {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oconversionFactor.value = 0.01
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Milimeters")}, {
                        oExpanded = false
                        outputUnit = "Milimeters"
                        oconversionFactor.value = 0.001
                        convertingUnit()
                    })
                    DropdownMenuItem(text = {Text("Feet")}, {
                        oExpanded = false
                        outputUnit = "Feet"
                        oconversionFactor.value = 0.3048
                        convertingUnit()
                    })

                }
            }
        }
        Spacer(Modifier.padding(16.dp))
        Text("Result : $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UniUnitConvertorPreview(){
    UnitConvertor()
}