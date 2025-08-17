package com.example.taskmanagementapp.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.User

data class UserWithProjects(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
    val projects: List<Project>
)
