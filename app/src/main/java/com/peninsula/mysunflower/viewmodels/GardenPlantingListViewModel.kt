package com.peninsula.mysunflower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.peninsula.mysunflower.data.PlantAndGardenPlantings
import com.peninsula.mysunflower.repository.GardenPlantingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GardenPlantingListViewModel @Inject internal constructor(gardenPlantingRespository: GardenPlantingRepository): ViewModel() {
    val plantAndGardenPlantings:LiveData<List<PlantAndGardenPlantings>> = gardenPlantingRespository.getPlantedGardens().asLiveData()
}