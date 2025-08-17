package com.example.taskmanagementapp.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.example.taskmanagementapp.entities.Attachment
import com.example.taskmanagementapp.entities.Task

data class TaskWithAttachments(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val attachments: List<Attachment>
)
