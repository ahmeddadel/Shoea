package com.dolla.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.dolla.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate xml file with this class
        val binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        binding.btNext.setOnClickListener { navigateToShoeList() }

        return binding.root
    }


    private fun navigateToShoeList() {
        val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}

