package com.peninsula.mysunflower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peninsula.mysunflower.data.PlantAndGardenPlantings
import com.peninsula.mysunflower.databinding.ListItemGardenPlantingBinding
import com.peninsula.mysunflower.fragment.HomeViewPagerFragment
import com.peninsula.mysunflower.fragment.HomeViewPagerFragmentDirections
import com.peninsula.mysunflower.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(
    GardenPlantDiffCallback()
){

    class ViewHolder(private val binding: ListItemGardenPlantingBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {view->
                binding.viewModel?.plantId?.let { plantId ->
                    navigateToPlant(plantId, view)
                }
            }
        }

        private fun navigateToPlant(plantId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }

        fun bind(plantings: PlantAndGardenPlantings){
            with(binding){
                viewModel = PlantAndGardenPlantingsViewModel(plantings)
                executePendingBindings()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemGardenPlantingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private class GardenPlantDiffCallback: DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }

}
//emulator -avd Pixel_3a_API_30_x86 -dns-server 8.8.8.8