package com.example.nameprediction.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nameprediction.databinding.ActivityPredictionBinding
import com.example.nameprediction.source.Repository
import com.example.nameprediction.source.NetworkDataProvider
import com.example.nameprediction.source.PreferenceProvider
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

        val preferenceProvider = PreferenceProvider(this)
        val retrofitService = NetworkDataProvider.getInstance()
        val repository = Repository(retrofitService, preferenceProvider)

        viewModel = ViewModelProvider(this, PredictionViewModelFactory(repository))
            .get(PredictionViewModel::class.java)

        setupListener()
        setupViewData()
    }

    private fun setupViewData() {
        with(viewModel) {
            prediction.observe(this@PredictionActivity, {
                if (it != null) binding.prediction.text = it.toString()
            })

            isLoading.observe(
                this@PredictionActivity,
                {
                    val visibility = if (it) View.VISIBLE else View.GONE
                    binding.progressBar.visibility = visibility
                    binding.predictButton.isEnabled = !it
                })

            snackBar.observe(this@PredictionActivity, {
                it?.let {
                    Toast.makeText(this@PredictionActivity, it, Toast.LENGTH_LONG).show()
                    viewModel.onSnackBarShown()
                }
            })
        }
    }

    private fun setupListener() {
        binding.predictButton.setOnClickListener {
            val userInput = binding.userInput.text?.toString()
            userInput?.let {
                viewModel.getDataFromRepo(it)
                binding.userInput.text = null
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveLastName()
    }
}