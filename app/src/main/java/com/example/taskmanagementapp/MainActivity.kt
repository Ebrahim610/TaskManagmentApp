package com.example.taskmanagementapp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskmanagementapp.Database.AppDatabase
import com.example.taskmanagementapp.RequirmentsTest.PerformanceTester
import com.example.taskmanagementapp.RequirmentsTest.runProjectPerformanceTest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(this)
        val projectDao = db.projectDao()

        lifecycleScope.launch(Dispatchers.IO) {
            //Req2_2(db)
            //Req2_3(db)



            //Req2_4
            // 1) Call suspend version (one-time snapshot)
            val projects = db.projectDao().getAllProjectsOnce()
            Log.d("DAO_TEST", "Suspend projects: $projects")

            // 2) Collect Flow version for a few seconds
            launch {
                db.projectDao().getAllProjectsFlow().collect { projectsList ->
                    Log.d("DAO_TEST", "Flow emission: $projectsList")
                }
            }

            delay(5000) // Collect for 5 seconds before Activity might finish



            //Req2_6
            runProjectPerformanceTest(projectDao)




        }
    }

}