package com.peninsula.mysunflower.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.peninsula.mysunflower.data.Plant
import com.peninsula.mysunflower.db.AppDatabase
import com.peninsula.mysunflower.utilities.PLANT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>(){}.type
                    val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                    Result.success()
                }
            }
        }catch (ex: Exception){
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }


    companion object{
        private const val TAG = "SeedDatabaseWorker"
    }
}
