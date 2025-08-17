package com.example.taskmanagementapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagementapp.entities.Task
import com.example.taskmanagementapp.relations.TaskWithAttachments
import com.example.taskmanagementapp.relations.TaskWithProjects

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(task: Task): Long

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>

    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskWithProjects(taskId: Long): List<TaskWithProjects>

    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskWithAttachments(taskId: Long): List<TaskWithAttachments>
}