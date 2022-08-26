package com.dolla.shoestore.screens.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.dolla.shoestore.databinding.FragmentShoeDetailBinding
import com.dolla.shoestore.models.SaveState
import com.dolla.shoestore.models.Shoe
import com.dolla.shoestore.models.ShoeViewModel
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate xml file with this class
        val binding = FragmentShoeDetailBinding.inflate(inflater, container, false)


        // Hide and show Buttons when soft keyboard is visible
        activity?.let { it ->
            KeyboardVisibilityEvent.setEventListener(
                it,
                KeyboardVisibilityEventListener() { isOpen: Boolean ->
                    if (isOpen) {
                        binding.btSave.visibility = View.INVISIBLE
                        binding.btCancel.visibility = View.INVISIBLE
                    } else {
                        binding.btSave.visibility = View.VISIBLE
                        binding.btCancel.visibility = View.VISIBLE
                    }
                })
        }


        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.shoeDetailViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        // Set variable data of the layout
        binding.shoeData = Shoe("", 0.0, "", "")

        binding.btCancel.setOnClickListener { navigateToShoeList() }

        // To get back to List Fragment when the saving process finish
        viewModel.saveState.observe(this as LifecycleOwner) { ss ->
            if (ss == SaveState.YES) {
                navigateToShoeList()
                viewModel.onEventSaveComplete()
            }
        }

        return binding.root
    }

    private fun navigateToShoeList() {
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}