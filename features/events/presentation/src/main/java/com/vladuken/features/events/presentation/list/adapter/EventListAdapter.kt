package com.vladuken.features.events.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemEventBinding
import com.vladuken.features.events.presentation.model.itemcallbacks.EventItemCallback

class EventListAdapter : PagingDataAdapter<Event, EventViewHolder>(EventItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

}


