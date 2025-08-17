package com.example.taskmanagementapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.taskmanagementapp.relations.ProjectTaskJunc

@Dao
interface JuncDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ref: ProjectTaskJunc)
}