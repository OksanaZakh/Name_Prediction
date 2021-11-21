package com.example.nameprediction.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nameprediction.R
import com.example.nameprediction.databinding.ActivityMainBinding
import com.example.nameprediction.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.checkNameBtn.setOnClickListener { startPredictionActivity() }

        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModel.name.observe(this, {
            val nameText = it ?: resources.getString(R.string.check_name_string)
            binding.lastName.text = nameText
        })
        mainViewModel.getNameFromSharedPref()
    }

    private fun startPredictionActivity() {
        val intent = Intent(this, PredictionActivity::class.java)
        startActivity(intent)
        finish()
    }
}