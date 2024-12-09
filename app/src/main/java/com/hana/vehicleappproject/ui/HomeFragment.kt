package com.hana.vehicleappproject.ui

import com.hana.vehicleappproject.adapter.JadwalAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hana.vehicleappproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var jadwalAdapter: JadwalAdapter
    private lateinit var tipsAdapter: TipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        jadwalAdapter = JadwalAdapter(getDummyJadwal())
        binding.rvJadwalPerawatan.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvJadwalPerawatan.adapter = jadwalAdapter

        tipsAdapter = TipsAdapter(getDummyTips())
        binding.rvTipsPerawatan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTipsPerawatan.adapter = tipsAdapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDummyJadwal(): List<String> {
        return listOf("Senin, 12 Desember", "Selasa, 13 Desember", "Rabu, 14 Desember")
    }

    private fun getDummyTips(): List<String> {
        return listOf(
            "Periksa tekanan ban secara rutin.",
            "Ganti oli setiap 2000 km.",
            "Pastikan rem dalam kondisi baik."
        )
    }
}
