package com.example.taskmanagementapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanagementapp.entities.Attachment

@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(attachment: Attachment): Long

    @Query("SELECT * FROM attachments WHERE taskId = :taskId")
    suspend fun getByTask(taskId: Long): List<Attachment>
}