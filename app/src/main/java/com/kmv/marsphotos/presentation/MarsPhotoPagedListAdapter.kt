package com.kmv.marsphotos.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmv.marsphotos.databinding.PhotoItemBinding
import com.kmv.marsphotos.entity.Photo

class MarsPhotoPagedListAdapter(
    private val onClick: (Photo) -> Unit
) : PagingDataAdapter<Photo, MarsPhotoViewHolder>(DiffUtilCallback()){
    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            rover.text = item?.rover?.name ?: ""
            camera.text = item?.camera?.name ?: ""
            sol.text = item?.sol.toString()
            date.text = item?.earth_date
            item?.let {
                Glide
                    .with(photo.context)
                    .load(it.img_src)
                    .into(photo)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class MarsPhotoViewHolder(val binding: PhotoItemBinding)
    : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem == newItem

}