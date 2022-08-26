package com.dolla.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dolla.shoestore.R
import com.dolla.shoestore.databinding.FragmentShoeListBinding
import com.dolla.shoestore.databinding.RowItemLayoutBinding
import com.dolla.shoestore.models.ShoeViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate xml file with this class
        val binding = FragmentShoeListBinding.inflate(inflater, container, false)

        // Observe the list of shoe items and Add any new item to this layout as a view child
        viewModel.shoeItem.observe(viewLifecycleOwner) {
            for (shoe in viewModel.shoeItem.value!!) {
                val innerBinding = RowItemLayoutBinding.inflate(layoutInflater)
                innerBinding.shoeData = shoe
                binding.shoelistLayout.addView(innerBinding.root)
            }
        }

        binding.btAddShoe.setOnClickListener { navigateToShoeDetail() }

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun navigateToShoeDetail() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}