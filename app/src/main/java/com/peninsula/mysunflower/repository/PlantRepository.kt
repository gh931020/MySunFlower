package com.peninsula.mysunflower.repository

import com.peninsula.mysunflower.dao.PlantDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) = plantDao.getPlantWithGrowZoneNumber(growZoneNumber)

}
