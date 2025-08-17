package com.example.taskmanagementapp.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task
import com.example.taskmanagementapp.relations.ProjectWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(project: Project): Long

    @Query("SELECT * FROM projects")
    suspend fun getAll(): List<Project>


    @Query("SELECT * FROM projects")
    fun getAllProjectsLive(): LiveData<List<Project>>


    @Query("SELECT * FROM tasks WHERE projectId = :projectId")
    fun getTasksForProjectFlow(projectId: Int): Flow<List<Task>>


    @Transaction
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectWithTasks(projectId: Long): List<ProjectWithTasks>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Query("SELECT * FROM projects")
    suspend fun getAllProjectsOnce(): List<Project>


    @Query("SELECT * FROM projects")
    fun getAllProjectsFlow(): Flow<List<Project>>




    @Query("""
        SELECT p.* FROM projects p
        JOIN tasks t ON p.id = t.projectId
        GROUP BY p.id
        HAVING COUNT(t.id) > 3
    """)
    fun getProjectsWithMoreThan3Tasks(): List<Project>


    @RawQuery
    fun getProjectsWithMoreThan3TasksRaw(query: SupportSQLiteQuery): List<Project>

}
