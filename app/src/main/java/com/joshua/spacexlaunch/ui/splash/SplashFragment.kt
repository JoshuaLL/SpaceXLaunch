package com.joshua.spacexlaunch.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding:FragmentSplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)

        splashViewModel.switchHome(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        splashViewModel.switchNav.observe(viewLifecycleOwner){
             findNavController().navigate(it)
        }

    }

}
