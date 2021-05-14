package com.peninsula.mysunflower.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.peninsula.mysunflower.data.UnsplashPhoto
import com.peninsula.mysunflower.databinding.ListItemGardenPlantingBinding
import com.peninsula.mysunflower.databinding.ListItemPhotoBinding

class GalleryAdapter : PagingDataAdapter<UnsplashPhoto, GalleryAdapter.GalleryViewHolder>(GalleryDiffCallback()){
    class GalleryViewHolder(private val binding: ListItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.photo?.let { photo->
                    val uri = Uri.parse(photo.user.attributionUrl)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    view.context.startActivity(intent)
                }
            }
        }
        fun bind(item: UnsplashPhoto){
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null){
            holder.bind(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
                ListItemPhotoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                )
        )
    }


}

class GalleryDiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem ==newItem
    }

}
