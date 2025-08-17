package com.example.taskmanagementapp.RequirmentsTest

import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.taskmanagementapp.DAO.ProjectDao

class PerformanceTester(private val projectDao: ProjectDao) {

    fun runPerformanceTest() {

        val roomStart = System.nanoTime()
        repeat(100) {
            projectDao.getProjectsWithMoreThan3Tasks()
        }
        val roomTime = System.nanoTime() - roomStart


        val rawQuery = SimpleSQLiteQuery(
            """
            SELECT p.* FROM projects p
            JOIN tasks t ON p.id = t.projectId
            GROUP BY p.id
            HAVING COUNT(t.id) > 3
            """
        )

        val rawStart = System.nanoTime()
        repeat(100) {
            projectDao.getProjectsWithMoreThan3TasksRaw(rawQuery)
        }
        val rawTime = System.nanoTime() - rawStart

        // Log results
        Log.d("PERF", "Room query: ${roomTime}ns")
        Log.d("PERF", "Raw query: ${rawTime}ns")
    }
}

fun runProjectPerformanceTest(projectDao: ProjectDao) {
    val test = PerformanceTester(projectDao)
    test.runPerformanceTest()
}
