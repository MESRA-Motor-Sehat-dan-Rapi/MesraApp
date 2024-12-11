package com.hana.vehicleappproject.ui.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.adapter.ReminderAdapter
import com.hana.vehicleappproject.databinding.ActivityDetailReminderBinding
import com.hana.vehicleappproject.databinding.FragmentReminderBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel

class ReminderFragment : Fragment() {
    private val viewModel: ReminderViewModel by viewModels()

    private var _binding: FragmentReminderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReminderBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReminderAdapter()
        binding.recyclerViewReminder.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewReminder.adapter = adapter

        viewModel.allReminders.observe(viewLifecycleOwner) { reminders ->
            reminders?.let { adapter.submitList(it) }
        }

        binding.fabAddReminder.setOnClickListener {
            findNavController().navigate(R.id.action_reminderFragment_to_addReminderActivity)
        }
    }
}
