package com.dolla.shoestore.screens.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.dolla.shoestore.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate xml file with this class
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Hide and show Hello text when soft keyboard is visible
        activity?.let { it ->
            KeyboardVisibilityEvent.setEventListener(
                it,
                KeyboardVisibilityEventListener() { isOpen: Boolean ->
                    if (isOpen) {
                        binding.tvHello.visibility = View.INVISIBLE
                        binding.tvSign.visibility = View.INVISIBLE
                    } else {
                        binding.tvHello.visibility = View.VISIBLE
                        binding.tvSign.visibility = View.VISIBLE
                    }
                })
        }

        binding.btLogin.setOnClickListener { loginPressed() }
        binding.btSignUp.setOnClickListener { signUpPressed() }

        return binding.root
    }

    private fun loginPressed() {
        hideKeyboard()
        if (inputChecker()) {
            navigateToShoeList()
        }
    }

    private fun signUpPressed() {
        hideKeyboard()
        if (inputChecker()) {
            navigateToWelcome()
        }
    }

    private fun navigateToWelcome() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(binding.etEmail.text.toString())
        findNavController(this).navigate(action)
    }

    private fun navigateToShoeList() {
        val action = LoginFragmentDirections.actionLoginFragmentToShoeListFragment()
        findNavController(this).navigate(action)
    }

    private fun inputChecker(): Boolean {
        val email = binding.etEmail.text
        val password = binding.etPassword.text

        if (email.isEmpty() || email.isBlank()) {
            binding.etEmail.requestFocus()
            etEmail.error = "E-mail is required!"
            binding.etPassword.setText("")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.requestFocus()
            etEmail.error = "Please provide a valid E-mail"
            binding.etPassword.setText("")
            return false
        }

        if (password != null) {
            if (password.isEmpty() || password.isBlank()) {
                binding.etPassword.requestFocus()
                etPassword.error = "Password is required!"
                binding.etPassword.setText("")
                return false
            }
        }

        return true
    }


    // Hide Keyboard
    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // callback called when MyFragment is Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }
    }

}