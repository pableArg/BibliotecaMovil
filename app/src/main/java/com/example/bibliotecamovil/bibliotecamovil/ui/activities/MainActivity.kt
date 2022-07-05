package com.example.bibliotecamovil.bibliotecamovil.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val favModel by viewModel<FavViewModel>()
    private val searchViewModel by viewModel<SearchViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setAppBar()
        getBetseller()
        validationInDataBase()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.findNavController()
        appBarConfiguration(navController)
        navigateInFragment(navController)


    }

    private fun appBarConfiguration(navController: NavController) {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun navigateInFragment(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment2 -> {
                    binding.bottomNavigationView.visibility = View.INVISIBLE
                }
                R.id.searchFragment, R.id.favouriteFragment, R.id.infoFragment, R.id.articlesFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun getBetseller() {
        searchViewModel.setBetseller()

    }

    private fun validationInDataBase() {
        if (favModel.idFavorite.isEmpty()) {
            favModel.setupBookDataBase()
        }
    }

}