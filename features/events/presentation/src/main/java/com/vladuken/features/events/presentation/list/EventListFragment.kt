package com.vladuken.features.events.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vladuken.features.events.data.api
import com.vladuken.features.events.data.usecases.NetworkFetchEventsUseCase
import com.vladuken.features.events.presentation.databinding.FragmentEventListBinding
import com.vladuken.features.events.presentation.list.adapter.EventListAdapter
import kotlinx.coroutines.flow.collect

class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding

    private lateinit var viewModel: BaseEventListViewModel

    private val adapter = EventListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = adapter


        //TODO change for di
        viewModel = EventListViewModel(NetworkFetchEventsUseCase(api))

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    is BaseEventListViewModel.EventsOutput.Success -> adapter.submitList(it.events)
                    is BaseEventListViewModel.EventsOutput.Failure -> {
                    }
                    is BaseEventListViewModel.EventsOutput.Loading -> {
                    }
                }
            }
        }
    }
}