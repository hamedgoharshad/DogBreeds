package com.near.presentation.nearby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.near.presentation.R
import com.near.presentation.databinding.NetworkStateItemBinding

class PlaceLoadStateAdapter(
    private val onclick: () -> Unit
) : LoadStateAdapter<NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(
            NetworkStateItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.network_state_item, parent, false
                )
            ),
            onclick
        )
    }
}