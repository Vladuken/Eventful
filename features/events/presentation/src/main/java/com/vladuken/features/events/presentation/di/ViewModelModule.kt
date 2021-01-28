package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.presentation.list.BaseEventListViewModel
import com.vladuken.features.events.presentation.list.EventListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


internal val viewModelModule = module {
    viewModel<BaseEventListViewModel> {
        EventListViewModel(fetchEventsUseCase = get())
    }
}