package com.hana.vehicleappproject.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.databinding.FragmentProfileBinding
import com.hana.vehicleappproject.ui.SignInActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModels<ProfileViewModel> {
//        ViewModelFactory.getInstance(requireContext())
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnLogout.setOnClickListener {
//            lifecycleScope.launch {
//                viewModel.logout()
//            }
//            startActivity(Intent(this.context, SignInActivity::class.java))
//        }
    }

}