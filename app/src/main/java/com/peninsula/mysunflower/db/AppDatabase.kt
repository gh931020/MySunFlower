package com.peninsula.mysunflower.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.peninsula.mysunflower.dao.GardenPlantingDao
import com.peninsula.mysunflower.dao.PlantDao
import com.peninsula.mysunflower.data.GardenPlanting
import com.peninsula.mysunflower.data.Plant
import com.peninsula.mysunflower.utilities.DATABASE_NAME
import com.peninsula.mysunflower.workers.SeedDatabaseWorker

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object{
        @Volatile
        private var instance: AppDatabase ?= null

        fun getInstance(context: Context): AppDatabase{
            return instance?: synchronized(this){
                instance?:buildDatabase(context).also{
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }
}