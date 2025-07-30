package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddVitalDialog(
    onSubmit: (Int, Int, Int, Float, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var systolic by remember { mutableStateOf("") }
    var diastolic by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add Vitals",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Row for Sys BP and Dia BP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = systolic,
                        onValueChange = { systolic = it },
                        label = { Text("Sys BP") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = diastolic,
                        onValueChange = { diastolic = it },
                        label = { Text("Dia BP") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it },
                    label = { Text("Heart Rate (bpm)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (in kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (systolic.isNotEmpty() &&
                            diastolic.isNotEmpty() &&
                            heartRate.isNotEmpty() &&
                            weight.isNotEmpty() &&
                            babyKicks.isNotEmpty()
                        ) {
                            onSubmit(
                                systolic.toInt(),
                                diastolic.toInt(),
                                heartRate.toInt(),
                                weight.toFloat(),
                                babyKicks.toInt()
                            )
                            onDismiss()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C4DCC))
                ) {
                    Text("Submit", color = Color.White)
                }
            }
        }
    }
}





//package com.example.myapplication.ui.screen
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//
//@Composable
//fun AddVitalDialog(
//    onSubmit: (Int, Int, Int, Float, Int) -> Unit,
//    onDismiss: () -> Unit
//) {
//    var systolic by remember { mutableStateOf("") }
//    var diastolic by remember { mutableStateOf("") }
//    var weight by remember { mutableStateOf("") }
//    var babyKicks by remember { mutableStateOf("") }
//    var heartRate by remember { mutableStateOf("") } // Even if not in figma, you can add if required
//
//    Dialog(onDismissRequest = onDismiss) {
//        Surface(
//            shape = MaterialTheme.shapes.medium,
//            tonalElevation = 6.dp,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(20.dp)
//                    .fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Add Vitals",
//                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
//                    color = Color.Black,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//
//                // Row for Sys BP and Dia BP
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    OutlinedTextField(
//                        value = systolic,
//                        onValueChange = { systolic = it },
//                        label = { Text("Sys BP") },
//                        modifier = Modifier.weight(1f)
//                    )
//                    OutlinedTextField(
//                        value = diastolic,
//                        onValueChange = { diastolic = it },
//                        label = { Text("Dia BP") },
//                        modifier = Modifier.weight(1f)
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = weight,
//                    onValueChange = { weight = it },
//                    label = { Text("Weight (in kg)") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = babyKicks,
//                    onValueChange = { babyKicks = it },
//                    label = { Text("Baby Kicks") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(
//                    onClick = {
//                        if (systolic.isNotEmpty() && diastolic.isNotEmpty() && weight.isNotEmpty() && babyKicks.isNotEmpty()) {
//                            onSubmit(
//                                systolic.toInt(),
//                                diastolic.toInt(),
//                                0, // Heart rate is not in this dialog based on Figma
//                                weight.toFloat(),
//                                babyKicks.toInt()
//                            )
//                            onDismiss()
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(45.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C4DCC)) // Purple button
//                ) {
//                    Text("Submit", color = Color.White)
//                }
//            }
//        }
//    }
//}
