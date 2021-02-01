package com.vladuken.features.events.presentation.events.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vladuken.features.events.presentation.databinding.FragmentFavoriteEventListBinding
import com.vladuken.features.events.presentation.events.BaseRecentEventListViewModel
import com.vladuken.features.events.presentation.events.adapter.FavoriteEventListAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

//TODO Maybe use recent event list fragment - they are almost the same

class FavoriteEventListFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteEventListBinding

    private val viewModel by viewModel<BaseRecentEventListViewModel>(
        named(BaseRecentEventListViewModel.Strategy.FAVORITE)
    )
    
    private val adapter = FavoriteEventListAdapter(
        onToggleClicked = { viewModel.toggleEvent(it) },
        onEventClicked = {
            val openEventInBrowserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(openEventInBrowserIntent)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { output ->
                when (output) {
                    is BaseRecentEventListViewModel.EventsOutput.Success -> {
                        adapter.submitList(output.events)
                    }
                    is BaseRecentEventListViewModel.EventsOutput.Failure -> {
                        //TODO process error
                        output.error.printStackTrace()
                    }
                    is BaseRecentEventListViewModel.EventsOutput.Loading -> {
                        //TODO process loading
                    }
                }
            }
        }

    }

}