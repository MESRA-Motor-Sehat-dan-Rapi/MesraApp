package com.hana.vehicleappproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.databinding.ItemReminderBinding
import com.hana.vehicleappproject.data.local.entity.Reminder

class ReminderAdapter(
    private var reminders: List<Reminder>,
    private val onReminderClick: (Reminder) -> Unit,
    private val onCheckmarkClick: (Reminder) -> Unit
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    inner class ReminderViewHolder(private val binding: ItemReminderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder) {
            binding.reminderTitle.text = reminder.title
            binding.reminderDate.text = reminder.date
            binding.reminderLocation.text = reminder.location

            // Update checkmark icon based on isChecked status
            val checkmarkIconRes = if (reminder.isChecked) {
                R.drawable.ic_checked // Replace with your checked icon resource
            } else {
                R.drawable.ic_unchecked // Replace with your unchecked icon resource
            }
            binding.checkmarkIcon.setImageResource(checkmarkIconRes)

            // Handle item click
            binding.root.setOnClickListener {
                onReminderClick(reminder)
            }

            // Handle checkmark click
            binding.checkmarkIcon.setOnClickListener {
                onCheckmarkClick(reminder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val binding = ItemReminderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(reminders[position])
    }

    override fun getItemCount(): Int = reminders.size

    fun updateData(newReminders: List<Reminder>) {
        reminders = newReminders
        notifyDataSetChanged()
    }
}