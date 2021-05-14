package com.peninsula.mysunflower.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.peninsula.mysunflower.R
import com.peninsula.mysunflower.adapters.GardenPlantingAdapter
import com.peninsula.mysunflower.adapters.PLANT_LIST_PAGE_INDEX
import com.peninsula.mysunflower.databinding.FragmentGardenBinding
import com.peninsula.mysunflower.viewmodels.GardenPlantingListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment: Fragment() {

    private lateinit var binding: FragmentGardenBinding

    private val viewModel: GardenPlantingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenBinding.inflate(inflater, container, false)

        val adapter = GardenPlantingAdapter()

        binding.gardenList.adapter = adapter

        binding.addPlantBtn.setOnClickListener {
            navigateToPlantListPage()
        }

        subscribUi(adapter, binding)

        return binding.root
    }

    private fun subscribUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner){result->
            binding.hasPlantings = result.isNullOrEmpty().not()
            adapter.submitList(result)
        }
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem = PLANT_LIST_PAGE_INDEX
    }

}
