@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color   // ✅ ADD THIS
import com.example.myapplication.data.db.VitalEntity
import com.example.myapplication.ui.viewmodel.VitalViewModel
import androidx.compose.ui.unit.dp


@Composable
fun VitalScreen(viewModel: VitalViewModel) {
    val vitals by viewModel.vitals.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Track My Pregnancy", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF9C4DCC))
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color(0xFF9C4DCC)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Vitals", tint = Color.White)
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(vitals) { vital ->
                VitalCard(vital)
            }
        }

        if (showDialog) {
            AddVitalDialog(
                onSubmit = { sys, dia, hr, wt, kicks -> viewModel.addVital(sys, dia, hr, wt, kicks) },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun VitalCard(vital: VitalEntity) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEBD8F7)),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("❤️  ${vital.heartRate} bpm")
                Text("🩺 ${vital.systolic}/${vital.diastolic} mmHg")
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("⚖️ ${vital.weight} kg")
                Text("👶 ${vital.babyKicks} kicks")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = java.text.SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", java.util.Locale.getDefault())
                    .format(vital.timestamp),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF9C4DCC)
            )
        }
    }
}
