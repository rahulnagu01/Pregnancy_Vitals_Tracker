package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.work.*
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.repository.VitalRepository
import com.example.myapplication.ui.screen.VitalScreen
import com.example.myapplication.ui.viewmodel.VitalViewModel
import com.example.myapplication.worker.ReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize database, repository, and ViewModel
        val dao = AppDatabase.getInstance(applicationContext).vitalDao()
        val repository = VitalRepository(dao)
        val viewModel = VitalViewModel(repository)

        // Request notification permission for Android 13+
        requestNotificationPermission {
            // Schedule notifications every 1 min for testing
            scheduleTestReminderWorker()
        }

        // Set UI content
        setContent {
            VitalScreen(viewModel)
        }
    }

    /**
     * Request POST_NOTIFICATIONS permission on Android 13+.
     */
    private fun requestNotificationPermission(onGranted: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                    if (granted) {
                        onGranted()
                    }
                }.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                onGranted()
            }
        } else {
            onGranted()
        }
    }

    /**
     * Testing: Schedule a notification every 1 minute.

     */
    private fun scheduleTestReminderWorker() {
        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(5, TimeUnit.HOURS) // Changed from 1 min to 5 hours
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork(
                "VitalsReminderTest",
                ExistingWorkPolicy.REPLACE,
                workRequest
            )

        // Re-run worker every 5 hours by observing completion
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(workRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    scheduleTestReminderWorker() // Reschedule for next 5 hours
                }
            }
    }

}
