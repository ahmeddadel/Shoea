package com.dolla.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.dolla.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Hide back button in Login and List fragment
        val appBarConfiguration =
            AppBarConfiguration.Builder(R.id.loginFragment, R.id.shoeListFragment).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)


        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.loginFragment -> {
                    navController.navigateUp()
                    navController.navigate(R.id.loginFragment)
                    true
                }
                else -> false
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.myNavHostFragment).navigateUp() || super.onSupportNavigateUp()
    }

}