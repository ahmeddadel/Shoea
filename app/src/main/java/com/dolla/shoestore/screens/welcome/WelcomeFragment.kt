package com.dolla.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.dolla.shoestore.R
import com.dolla.shoestore.databinding.FragmentWelcomeBinding
import com.dolla.shoestore.models.WelcomeViewModel
import timber.log.Timber

class WelcomeFragment : Fragment() {

    lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    lateinit var fadeAnimation: Animation
    private var clickNextCounter: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate xml file with this class
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        Timber.i("Called ViewModelProvider!")
        // Creating new instance for GameViewModel
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        // To observe the images and welcome messages
        clickNextCounter = viewModel.clickNextCounter

        // Animation when slide between images
        fadeAnimation = AnimationUtils.loadAnimation(activity, R.anim.fade_in)

        val userEmail = WelcomeFragmentArgs.fromBundle(requireArguments()).email
        binding.tvUserEmail.text = userEmail.subSequence(0, userEmail.indexOf("@"))

        // Set the first image and welcome message
        binding.tvAboutStore.text = getText(viewModel.messageList[clickNextCounter])
        binding.ivWelcomePhotos.setImageResource(viewModel.photosList[clickNextCounter])


        binding.btGetStarted.setOnClickListener { onGetStartedPressed() }

        return binding.root
    }


    private fun onGetStartedPressed() {

        clickNextCounter++

        if (clickNextCounter < 3) {
            binding.apply {
                tvAboutStore.text = getText(viewModel.messageList[clickNextCounter])
                tvAboutStore.startAnimation(fadeAnimation)
                ivWelcomePhotos.setImageResource(viewModel.photosList[clickNextCounter])
                ivWelcomePhotos.startAnimation(fadeAnimation)
                if (clickNextCounter == 2)
                    btGetStarted.text = getText(R.string.get_started)
            }
        } else
            navigateToInstructions()
    }

    private fun navigateToInstructions() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}