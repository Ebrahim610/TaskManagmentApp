package com.example.taskmanagementapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks",
    foreignKeys = [
        ForeignKey(entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],

        )
    ],
    indices = [Index(value = ["projectId"])]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val description: String,
    val projectId: Long?,
    val tags: List<String>, // remove to test req2.2
    val createdAt: Date // remove to test req2.2
)
