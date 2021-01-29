package com.vladuken.features.events.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemEventBinding
import java.text.SimpleDateFormat
import java.util.*

//Todo add normal formatting with TimeZones
private val simpleDateFormatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.ROOT)

class EventViewHolder(
    private val binding: ItemEventBinding,
    private val onToggleClicked: (Event) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event) {
        with(binding) {
            tvEventTitle.text = event.title
            tvEventDate.text = simpleDateFormatter.format(event.date)
            tbEventIsFavorite.isChecked = event.isFavorite
            tbEventIsFavorite.setOnClickListener { onToggleClicked(event) }
        }
    }

}
