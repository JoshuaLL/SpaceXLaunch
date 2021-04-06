package com.joshua.spacexlaunch.ui.launches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.databinding.FragmentLaunchesBinding

class LaunchesFragment : Fragment(R.layout.fragment_launches) {

    private lateinit var binding: FragmentLaunchesBinding
    private val dashboardViewModel: LaunchesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLaunchesBinding.bind(view)
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            binding.textLaunches.text = it
        }
    }
}