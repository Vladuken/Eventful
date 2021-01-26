package com.vladuken.features.events.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemEventBinding

class EventViewHolder(
    private val binding: ItemEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event) {
        binding.tvEventTitle.text = event.title
        binding.tvEventDate.text = event.title
    }

}
