package com.example.taskmanagementapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "attachments",
    foreignKeys = [
        ForeignKey(entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],

        )
    ],
    indices = [Index(value = ["taskId"])]
)
data class Attachment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val filePath: String,
    val taskId: Long
)
