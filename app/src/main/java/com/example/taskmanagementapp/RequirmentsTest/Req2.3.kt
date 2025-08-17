package com.example.taskmanagementapp.RequirmentsTest
import android.util.Log
import com.example.taskmanagementapp.Database.AppDatabase
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task
import com.example.taskmanagementapp.entities.User
import java.util.Date


 suspend fun Req2_3(db: AppDatabase) {
    val userDao = db.userDao()
    val projectDao = db.projectDao()
    val taskDao = db.taskDao()


    val userId = userDao.insert(User(name = "Alice", email = "alice@example.com"))


    val projId = projectDao.insert(Project(title = "Compose Migration", ownerId = userId))


    taskDao.insert(
        Task(
            description = "Refactor UI",
            projectId = projId,
            tags = listOf("UI", "JetpackCompose"),
            createdAt = Date()
        )
    )

    taskDao.insert(
        Task(
            description = "Update Gradle",
            projectId = projId,
            tags = listOf("Build", "Gradle"),
            createdAt = Date()
        )
    )


    val result = projectDao.getProjectWithTasks(projId)
    Log.d("DB_TEST", "Project with Tasks: $result")
}