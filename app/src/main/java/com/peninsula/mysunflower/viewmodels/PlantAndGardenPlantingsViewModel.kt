package com.peninsula.mysunflower.viewmodels

import com.peninsula.mysunflower.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*


class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) {
    private val plant = checkNotNull(plantings.plant)
    private val gardenPlantings = plantings.gardenPlantings[0]

    val waterDateString = dateFormat.format(gardenPlantings.plantDate.time)

    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(gardenPlantings.plantDate.time)
    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}