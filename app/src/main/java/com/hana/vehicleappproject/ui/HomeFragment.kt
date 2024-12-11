package com.hana.vehicleappproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hana.vehicleappproject.adapter.ReminderAdapter
import com.hana.vehicleappproject.databinding.FragmentHomeBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReminderViewModel by viewModels()
    private lateinit var reminderAdapter: ReminderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Inisialisasi ReminderAdapter
        reminderAdapter = ReminderAdapter()
        binding.rvJadwalPerawatan.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvJadwalPerawatan.adapter = reminderAdapter

        // Observasi data dari ViewModel
        viewModel.getUncheckedReminders().observe(viewLifecycleOwner, Observer { reminders ->
            reminderAdapter.submitList(reminders)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
