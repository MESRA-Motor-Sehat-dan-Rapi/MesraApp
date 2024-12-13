package com.hana.vehicleappproject.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.adapter.ReminderAdapter
import com.hana.vehicleappproject.data.local.entity.Reminder
import com.hana.vehicleappproject.data.notification.NotificationReceiver
import com.hana.vehicleappproject.databinding.FragmentReminderBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ReminderFragment : Fragment(R.layout.fragment_reminder) {
    private lateinit var binding: FragmentReminderBinding
    private lateinit var viewModel: ReminderViewModel
    private lateinit var adapter: ReminderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        viewModel = ViewModelProvider(requireActivity())[ReminderViewModel::class.java]

        // Set up RecyclerView
        adapter = ReminderAdapter(emptyList(), this::onReminderClick, this::onCheckmarkClick)
        binding.recyclerViewReminder.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewReminder.adapter = adapter

        // Observe data from ViewModel
        viewModel.allReminders.observe(viewLifecycleOwner) { reminders ->
            updateCheckmarks(reminders)
            adapter.updateData(reminders)
        }

        // Handle Add Reminder button
        binding.fabAddReminder.setOnClickListener {
            val intent = Intent(requireContext(), AddReminderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onReminderClick(reminder: Reminder) {
        val intent = Intent(requireContext(), DetailReminderActivity::class.java)
        intent.putExtra("EXTRA_REMINDER", reminder) // Kirim data Reminder
        startActivity(intent)
    }

    private fun onCheckmarkClick(reminder: Reminder) {
        reminder.isChecked = true
        viewModel.update(reminder)
    }

    private fun updateCheckmarks(reminders: List<Reminder>) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        reminders.forEach { reminder ->
            try {
                // Jika reminder.date kosong, skip
                if (reminder.date.isNullOrEmpty()) return@forEach

                if (reminder.date < currentDate && !reminder.isChecked) {
                    reminder.isChecked = true
                    viewModel.update(reminder)
                }

                scheduleNotification(reminder)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun scheduleNotification(reminder: Reminder) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), NotificationReceiver::class.java).apply {
            putExtra("title", reminder.title)
            putExtra("description", reminder.description)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            reminder.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val reminderDate = try {
            // Cek null dan parsing tanggal
            if (reminder.date.isNullOrEmpty()) return
            dateFormat.parse(reminder.date)?.time
        } catch (e: ParseException) {
            e.printStackTrace()
            return
        }

        // Cek apakah reminderDate tidak null
        if (reminderDate != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderDate, pendingIntent)
        }
    }
}
