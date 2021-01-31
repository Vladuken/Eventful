package com.vladuken.features.events.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemEventBinding
import com.vladuken.features.events.presentation.model.itemcallbacks.EventItemCallback
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent

class FavoriteEventListAdapter(
    private val onToggleClicked: (Event) -> Unit
) : ListAdapter<FavoriteEvent, FavoriteEventViewHolder>(EventItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteEventViewHolder(binding, onToggleClicked)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


