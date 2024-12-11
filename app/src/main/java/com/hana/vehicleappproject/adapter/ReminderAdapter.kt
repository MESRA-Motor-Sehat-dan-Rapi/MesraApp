package com.hana.vehicleappproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hana.vehicleappproject.databinding.ItemReminderBinding
import com.hana.vehicleappproject.ui.reminder.Reminder

class ReminderAdapter :
    ListAdapter<Reminder, ReminderAdapter.ReminderViewHolder>(REMINDER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val binding = ItemReminderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = getItem(position)
        holder.bind(reminder)
    }

    inner class ReminderViewHolder(private val binding: ItemReminderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Binding menggunakan ItemReminderBinding
        fun bind(reminder: Reminder) {
            binding.reminderTitle.text = reminder.title
            binding.reminderDate.text = reminder.date
            binding.reminderIcon.setImageResource(reminder.iconRes)
        }
    }

    companion object {
        private val REMINDER_COMPARATOR = object : DiffUtil.ItemCallback<Reminder>() {
            override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder) = oldItem == newItem
        }
    }
}
