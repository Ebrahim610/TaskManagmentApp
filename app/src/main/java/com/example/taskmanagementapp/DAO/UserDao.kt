package com.example.taskmanagementapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagementapp.entities.User
import com.example.taskmanagementapp.relations.UserWithProjects

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(users: List<User>): List<Long>

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getUsersWithProjects(): List<UserWithProjects>
}