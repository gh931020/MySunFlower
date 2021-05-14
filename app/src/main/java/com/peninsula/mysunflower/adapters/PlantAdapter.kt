package com.peninsula.mysunflower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peninsula.mysunflower.bindingadapter.bindIsGone
import com.peninsula.mysunflower.data.Plant
import com.peninsula.mysunflower.databinding.ListItemPlantBinding
import com.peninsula.mysunflower.fragment.HomeViewPagerFragmentDirections

class PlantAdapter: ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
                ListItemPlantBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                )
        )
    }

    class PlantViewHolder(private val binding: ListItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {view->
                binding.plant?.let { plant->
                    navigateToPlant(plant,view)
                }

            }
        }

        private fun navigateToPlant(plant: Plant, view: View) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plant.plantId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Plant){
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

}

class PlantDiffCallback: DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}
