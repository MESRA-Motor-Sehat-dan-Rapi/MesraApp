package com.hana.vehicleappproject.ui.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hana.vehicleappproject.R

class ReminderFragment : Fragment() {
    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReminderAdapter()
        recyclerViewReminder.layoutManager = LinearLayoutManager(context)
        recyclerViewReminder.adapter = adapter

        viewModel.allReminders.observe(viewLifecycleOwner) { reminders ->
            reminders?.let { adapter.submitList(it) }
        }

        fabAddReminder.setOnClickListener {
            findNavController().navigate(R.id.action_reminderFragment_to_addReminderActivity)
        }
    }
}
