package com.vladuken.features.events.presentation.list

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vladuken.features.events.presentation.databinding.FragmentEventListBinding
import com.vladuken.features.events.presentation.list.adapter.EventListAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel


class EventListFragment : Fragment() {

    private lateinit var binding: FragmentEventListBinding

    private val viewModel by viewModel<BaseEventListViewModel>()

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


        //TODO remove and change for alert dialog
        val dialog = ProgressDialog(requireContext())
            .apply {
                setMessage("Please wait...")
                setCancelable(false)
            }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { output ->
                when (output) {
                    is BaseEventListViewModel.EventsOutput.Success -> {
                        dialog.hide()
                        adapter.submitList(output.events)
                    }
                    is BaseEventListViewModel.EventsOutput.Failure -> {
                        dialog.hide()
                        output.error.printStackTrace()
                    }
                    is BaseEventListViewModel.EventsOutput.Loading -> {
                        dialog.show()
                    }
                }
            }
        }
    }
}