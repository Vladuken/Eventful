package com.vladuken.features.events.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladuken.features.events.presentation.databinding.ItemLoaderStateBinding

class LoadingStateViewHolder(
    binding: ItemLoaderStateBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): LoadingStateViewHolder {
            return LoadingStateViewHolder(
                ItemLoaderStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}