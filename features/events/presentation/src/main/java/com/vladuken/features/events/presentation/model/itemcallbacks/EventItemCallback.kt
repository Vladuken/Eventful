package com.vladuken.features.events.presentation.model.itemcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.vladuken.features.events.domain.models.Event

class EventItemCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

}
