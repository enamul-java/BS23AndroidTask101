package com.ehaquesoft.bs23androidtask101.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepo
import com.ehaquesoft.bs23androidtask101.data.entities.GitRepoOwner

@Database(entities = [GitRepo::class,GitRepoOwner::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gitRepoDao(): GitRepoDao
    abstract fun gitRepoOwnerDao(): GitRepoOwnerDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "gitrepodb")
                .fallbackToDestructiveMigration()
                .build()
    }

}