package com.example.taskmanagementapp.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task

@Entity(
    tableName = "project_task_Junc",
    primaryKeys = ["projectId", "taskId"],
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],

        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["projectId"]),
        Index(value = ["taskId"])
    ]
)
data class ProjectTaskJunc(
    val projectId: Long,
    val taskId: Long
)
