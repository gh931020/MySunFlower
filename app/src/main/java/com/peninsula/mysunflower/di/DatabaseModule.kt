package com.peninsula.mysunflower.di

import android.content.Context
import androidx.room.PrimaryKey
import com.peninsula.mysunflower.dao.GardenPlantingDao
import com.peninsula.mysunflower.dao.PlantDao
import com.peninsula.mysunflower.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun privideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providePlantDao(appDatabase: AppDatabase): PlantDao{
        return appDatabase.plantDao()
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): GardenPlantingDao{
        return appDatabase.gardenPlantingDao()
    }

}