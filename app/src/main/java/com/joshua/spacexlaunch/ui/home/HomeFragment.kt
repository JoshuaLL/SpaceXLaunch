package com.joshua.spacexlaunch.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.databinding.FragmentHomeBinding
import com.joshua.spacexlaunch.extensions.BackButtonBehaviour
import com.joshua.spacexlaunch.extensions.setupWithNavController

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private val bottomNavSelectedItemIdKey = "BOTTOM_NAV_SELECTED_ITEM_ID_KEY"
    private var bottomNavSelectedItemId = R.id.nav_launches // Must be your starting destination


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        savedInstanceState?.let {
            bottomNavSelectedItemId =
                savedInstanceState.getInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        }
        setupBottomNavBar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun setupBottomNavBar() {

        // Your navGraphIds must have the same ids as your menuItem ids
        val navGraphIds = listOf(R.navigation.nav_launches, R.navigation.nav_about)

        binding.navView.apply {
            binding.navView.selectedItemId = bottomNavSelectedItemId // Needed to maintain correct state on return

            addToolbarListener(binding.bottomNavToolbar)

            val controller = setupWithNavController(
                fragmentManager = childFragmentManager,
                navGraphIds = navGraphIds,
                backButtonBehaviour = BackButtonBehaviour.POP_HOST_FRAGMENT,
                containerId = R.id.bottom_nav_container,
                firstItemId = R.id.nav_launches,
                intent = requireActivity().intent
            )

            controller.observe(viewLifecycleOwner, { navController ->
                NavigationUI.setupWithNavController(binding.bottomNavToolbar, navController)
                bottomNavSelectedItemId = navController.graph.id
            })
        }




    }

    private fun addToolbarListener(toolbar: Toolbar) {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings -> {
                    findNavController().navigate(R.id.settings)
                    true
                }
                else -> false
            }
        }
    }
}