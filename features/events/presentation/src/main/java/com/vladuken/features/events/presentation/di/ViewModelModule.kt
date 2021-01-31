package com.vladuken.features.events.presentation.di

import com.vladuken.features.events.domain.usecases.FetchRecentEventsUseCase
import com.vladuken.features.events.presentation.list.BaseEventListViewModel
import com.vladuken.features.events.presentation.list.EventListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val viewModelModule = module {
    viewModel<BaseEventListViewModel> {
        EventListViewModel(
            fetchRecentEventsUseCase = get(named(FetchRecentEventsUseCase.FetchType.CLEAR_AFTER_ONE_HOUR)),
            fetchFavoriteEventsUseCase = get(),
            clearCacheRecentEventsUseCase = get(),
            toggleEventUseCase = get()
        )
    }
}