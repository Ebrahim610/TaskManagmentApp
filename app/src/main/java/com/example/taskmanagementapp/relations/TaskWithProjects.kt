package com.example.taskmanagementapp.relations
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task

data class TaskWithProjects(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ProjectTaskJunc::class,
            parentColumn = "taskId",
            entityColumn = "projectId"
        )
    )
    val projects: List<Project>
)
