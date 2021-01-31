package com.vladuken.features.events.presentation.model.itemcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent

class FavoriteEventItemCallback : DiffUtil.ItemCallback<FavoriteEvent>() {

    override fun areItemsTheSame(
        oldItem: FavoriteEvent,
        newItem: FavoriteEvent
    ): Boolean {
        return oldItem.event.id == newItem.event.id
    }

    override fun areContentsTheSame(
        oldItem: FavoriteEvent,
        newItem: FavoriteEvent
    ): Boolean {
        return oldItem == newItem
    }
}
