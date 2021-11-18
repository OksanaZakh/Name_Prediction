package com.example.nameprediction.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nameprediction.R
import com.example.nameprediction.databinding.ActivityMainBinding
import com.example.nameprediction.source.NetworkDataProvider
import com.example.nameprediction.source.PreferenceProvider
import com.example.nameprediction.source.Repository
import com.example.nameprediction.viewmodel.MainViewModel
import com.example.nameprediction.viewmodel.MainViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.checkNameBtn.setOnClickListener { startPredictionActivity() }

        val preferenceProvider = PreferenceProvider(this)
        val retrofitService = NetworkDataProvider.getInstance()
        val repository = Repository(retrofitService, preferenceProvider)

        viewModel = ViewModelProvider(this, MainViewModelProvider(repository))
            .get(MainViewModel::class.java)

        viewModel.name.observe(this, {
            val nameText = it ?: resources.getString(R.string.check_name_string)
            binding.lastName.text = nameText
        })

        viewModel.getNameFromSharedPref()
    }

    private fun startPredictionActivity() {
        val intent = Intent(this, PredictionActivity::class.java)
        startActivity(intent)
        finish()
    }
}