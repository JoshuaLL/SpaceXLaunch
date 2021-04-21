package com.joshua.spacexlaunch.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joshua.spacexlaunch.R
//import com.joshua.spacexlaunch.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {
//    private lateinit var binding:FragmentAboutBinding

    private val aboutViewModel: AboutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentAboutBinding.bind(view)
//        aboutViewModel.text.observe(viewLifecycleOwner){
//            binding.textAbout.text =it
//        }
    }
}