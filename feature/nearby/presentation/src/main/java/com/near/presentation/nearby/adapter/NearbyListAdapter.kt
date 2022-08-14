package com.near.presentation.nearby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.near.domain.model.Place
import com.near.presentation.databinding.ItemPlaceBinding

internal class NearbyListAdapter constructor(private val onClick: (Place) -> Unit) :
    PagingDataAdapter<Place, NearbyListAdapter.PlaceViewHolder>(Companion) {

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClick) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean =
            oldItem == newItem
    }

    internal class PlaceViewHolder(
        private val binding: ItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Place, onClick: (Place) -> Unit) {
                binding.run {
                    place = item
                    root.setOnClickListener {
                        onClick(item)
                    }
                    executePendingBindings()
                }
        }
    }
}



