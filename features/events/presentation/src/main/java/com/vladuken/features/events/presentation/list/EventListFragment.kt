package com.vladuken.features.events.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vladuken.features.events.presentation.R
import kotlinx.coroutines.flow.collect

class EventListFragment : Fragment() {

    private lateinit var viewModel: BaseEventListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO change for di
        viewModel = ViewModelProvider(this).get(EventListViewModel::class.java)

        lifecycleScope.launchWhenCreated {

            viewModel.state.collect {
                when (it) {
                    is BaseEventListViewModel.EventsOutput.Success -> {
                        Toast.makeText(
                            requireContext(),
                            it.events.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is BaseEventListViewModel.EventsOutput.Failure -> TODO()
                }
            }

        }
    }
}