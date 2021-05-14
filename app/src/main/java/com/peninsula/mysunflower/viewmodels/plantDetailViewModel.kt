package com.peninsula.mysunflower.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.peninsula.mysunflower.BuildConfig
import com.peninsula.mysunflower.repository.GardenPlantingRepository
import com.peninsula.mysunflower.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel
    @Inject constructor(saveStateHandle: SavedStateHandle,
                        plantRepository: PlantRepository,
                        private val gardenPlantingRepository: GardenPlantingRepository) : ViewModel() {

    companion object{
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }

    val plantId: String = saveStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!

    val isPlanted = gardenPlantingRepository.isPlanted(plantId).asLiveData()

    val plant = plantRepository.getPlant(plantId).asLiveData()

    fun addPlantToGarden(){
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")
}