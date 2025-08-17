package com.example.taskmanagementapp.relations
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task

data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ProjectTaskJunc::class,
            parentColumn = "projectId",
            entityColumn = "taskId"
        )
    )
    val tasks: List<Task>
)
