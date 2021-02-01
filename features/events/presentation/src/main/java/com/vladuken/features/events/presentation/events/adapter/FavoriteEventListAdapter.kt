package com.vladuken.features.events.presentation.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemFavoriteEventBinding
import com.vladuken.features.events.presentation.model.itemcallbacks.FavoriteEventItemCallback
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent

class FavoriteEventListAdapter(
    private val onToggleClicked: (Event) -> Unit,
    private val onEventClicked: (Event) -> Unit
) : ListAdapter<FavoriteEvent, FavoriteEventViewHolder>(FavoriteEventItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val binding = ItemFavoriteEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteEventViewHolder(binding, onToggleClicked, onEventClicked)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


