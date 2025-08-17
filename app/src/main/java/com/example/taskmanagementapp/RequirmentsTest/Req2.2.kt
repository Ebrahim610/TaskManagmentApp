package com.example.taskmanagementapp.RequirmentsTest
import android.util.Log
import com.example.taskmanagementapp.Database.AppDatabase
import com.example.taskmanagementapp.entities.Attachment
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task
import com.example.taskmanagementapp.entities.User
import com.example.taskmanagementapp.relations.ProjectTaskJunc
import java.util.Date

suspend fun Req2_2(db: AppDatabase) {
    val userDao = db.userDao()
    val projectDao = db.projectDao()
    val taskDao = db.taskDao()
    val attachmentDao = db.attachmentDao()
    val juncdao = db.juncDao()

    val user1Id = userDao.insert(User(name = "Ebrahim", email = "ebrahim@gmail.com"))
    val user2Id = userDao.insert(User(name = "Gamal", email = "Gamal@example.com"))
    Log.d("DB_TEST", "Inserted User IDs: $user1Id, $user2Id")

    val projAId = projectDao.insert(Project(title = "Website Revamp", ownerId = user1Id))
    val projBId = projectDao.insert(Project(title = "Mobile App", ownerId = user2Id))
    Log.d("DB_TEST", "Inserted Project IDs: $projAId, $projBId")

    val t1Id = taskDao.insert(Task(description = "Design landing page", projectId = projAId,tags = listOf("UI", "JetpackCompose"),
        createdAt = Date()))
    val t2Id = taskDao.insert(Task(description = "Implement auth flow", projectId = null,tags = listOf("Build", "Gradle"),
        createdAt = Date()))
    Log.d("DB_TEST", "Inserted Task IDs: $t1Id, $t2Id")

    juncdao.insert(ProjectTaskJunc(projectId = projAId, taskId = t1Id))
    juncdao.insert(ProjectTaskJunc(projectId = projBId, taskId = t1Id))
    juncdao.insert(ProjectTaskJunc(projectId = projBId, taskId = t2Id))

    val a1Id = attachmentDao.insert(Attachment(filePath = "/sdcard/Pictures/landing_wireframe.png", taskId = t1Id))
    val a2Id = attachmentDao.insert(Attachment(filePath = "/sdcard/Documents/specs.pdf", taskId = t1Id))
    Log.d("DB_TEST", "Inserted Attachment IDs: $a1Id, $a2Id")

    userDao.getUsersWithProjects().forEach {
        Log.d("DB_TEST", "UserWithProjects: ${it.user} -> ${it.projects}")
    }

    projectDao.getProjectWithTasks(projAId).forEach {
        Log.d("DB_TEST", "ProjectWithTasks(A): ${it.project} -> ${it.tasks}")
    }
    projectDao.getProjectWithTasks(projBId).forEach {
        Log.d("DB_TEST", "ProjectWithTasks(B): ${it.project} -> ${it.tasks}")
    }

    taskDao.getTaskWithProjects(t1Id).forEach {
        Log.d("DB_TEST", "TaskWithProjects(T1): ${it.task} <- ${it.projects}")
    }
    taskDao.getTaskWithAttachments(t1Id).forEach {
        Log.d("DB_TEST", "TaskWithAttachments(T1): ${it.task} -> ${it.attachments}")
    }
}

