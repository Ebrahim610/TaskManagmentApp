package com.example.taskmanagementapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "projects",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],

        )
    ],
    indices = [Index(value = ["ownerId"])]
)
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val ownerId: Long
)
