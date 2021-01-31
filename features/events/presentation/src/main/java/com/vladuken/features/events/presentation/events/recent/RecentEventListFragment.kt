package com.vladuken.features.events.presentation.events.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vladuken.features.events.presentation.databinding.FragmentRecentEventListBinding
import com.vladuken.features.events.presentation.events.adapter.FavoriteEventListAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel


class RecentEventListFragment : Fragment() {

    private lateinit var binding: FragmentRecentEventListBinding

    private val viewModel by viewModel<BaseRecentEventListViewModel>()

    private val adapter = FavoriteEventListAdapter {
        viewModel.toggleEvent(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = adapter
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { output ->
                when (output) {
                    is BaseRecentEventListViewModel.EventsOutput.Success -> {
                        binding.swipeToRefresh.isRefreshing = false
                        adapter.submitList(output.events)
                    }
                    is BaseRecentEventListViewModel.EventsOutput.Failure -> {
                        binding.swipeToRefresh.isRefreshing = false
                        //TODO process error
                        output.error.printStackTrace()
                    }
                    is BaseRecentEventListViewModel.EventsOutput.Loading -> {
                        binding.swipeToRefresh.isRefreshing = true
                    }
                }
            }
        }

    }

}