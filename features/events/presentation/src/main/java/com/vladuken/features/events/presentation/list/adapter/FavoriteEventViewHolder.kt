package com.vladuken.features.events.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.vladuken.features.events.domain.models.Event
import com.vladuken.features.events.presentation.databinding.ItemEventBinding
import com.vladuken.features.events.presentation.model.itemcallbacks.entity.FavoriteEvent
import java.text.SimpleDateFormat
import java.util.*

//Todo add normal formatting with TimeZones
private val simpleDateFormatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.ROOT)

class FavoriteEventViewHolder(
    private val binding: ItemEventBinding,
    private val onToggleClicked: (Event) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favoriteEvent: FavoriteEvent) {
        with(binding) {
            tvEventTitle.text = favoriteEvent.event.title
            tvEventDate.text = simpleDateFormatter.format(favoriteEvent.event.date)
            tbEventIsFavorite.isChecked = favoriteEvent.isFavorite
            tbEventIsFavorite.setOnClickListener { onToggleClicked(favoriteEvent.event) }
        }
    }

}
