package com.example.rickmorty.UI.view

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout = binding.drawerLayout
        val navigationView = binding.navView
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.charactersFragmentMenuButton -> navigateCharactersFragment(navController)
                R.id.episodesFragmentMenuButton -> navigateEpisodesFragment(navController)
                R.id.locationsFragmentMenuButton -> navigateLocationsFragment(navController)
                else -> {
                    true
                }
            }

        }




    }


    private fun navigateCharactersFragment(navControler:NavController):Boolean{
        navControler.navigate(R.id.nav_characters)
        return true
    }
    private fun navigateEpisodesFragment(navControler:NavController):Boolean{
        navControler.navigate(R.id.nav_episodes)
        return true
    }
    private fun navigateLocationsFragment(navControler:NavController):Boolean{
        navControler.navigate(R.id.nav_locations)
        return true
    }
}

