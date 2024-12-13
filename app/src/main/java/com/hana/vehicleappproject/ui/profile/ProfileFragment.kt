package com.hana.vehicleappproject.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hana.vehicleappproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // Inisialisasi variabel untuk data nama dan email
    private var userName: String? = null
    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Ambil data nama dan email dari Bundle yang dikirim
        arguments?.let {
            userName = it.getString("USER_NAME")
            userEmail = it.getString("USER_EMAIL")
        }

        // Set data ke TextView
        binding.tvFullName.text = userName ?: "Nama Tidak Tersedia"
        binding.tvEmailAddress.text = userEmail ?: "Email Tidak Tersedia"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle tombol logout jika diperlukan
        binding.btnLogout.setOnClickListener {
            // Tambahkan logika logout jika diperlukan
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
