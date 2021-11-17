package com.example.nameprediction.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nameprediction.databinding.ActivityPredictionBinding
import com.example.nameprediction.viewmodel.PredictionViewModel
import com.example.nameprediction.viewmodel.PredictionViewModelFactory

class PredictionActivity : AppCompatActivity() {

    lateinit var binding: ActivityPredictionBinding
    lateinit var viewModel: PredictionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, PredictionViewModelFactory()).get(PredictionViewModel::class.java)
    }
}