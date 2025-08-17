package com.example.taskmanagementapp.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagementapp.DAO.AttachmentDao
import com.example.taskmanagementapp.DAO.JuncDao
import com.example.taskmanagementapp.DAO.ProjectDao
import com.example.taskmanagementapp.DAO.TaskDao
import com.example.taskmanagementapp.DAO.UserDao
import com.example.taskmanagementapp.entities.Attachment
import com.example.taskmanagementapp.entities.Project
import com.example.taskmanagementapp.entities.Task
import com.example.taskmanagementapp.entities.User
import com.example.taskmanagementapp.relations.ProjectTaskJunc

@Database(
    entities = [User::class, Project::class, Task::class, Attachment::class, ProjectTaskJunc::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
    abstract fun attachmentDao(): AttachmentDao

    abstract fun juncDao(): JuncDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}