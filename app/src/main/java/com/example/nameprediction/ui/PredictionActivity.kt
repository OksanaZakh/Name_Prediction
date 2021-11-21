package com.example.nameprediction.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.nameprediction.databinding.ActivityPredictionBinding
import com.example.nameprediction.viewmodel.PredictionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PredictionActivity : AppCompatActivity() {

    lateinit var binding: ActivityPredictionBinding
    private val predictionViewModel: PredictionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListener()
        setupViewData()
    }

    private fun setupViewData() {
        with(predictionViewModel) {
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
                    predictionViewModel.onSnackBarShown()
                }
            })
        }
    }

    private fun setupListener() {
        binding.predictButton.setOnClickListener {
            val userInput = binding.userInput.text?.toString()
            userInput?.let {
                predictionViewModel.getDataFromRepo(it)
                binding.userInput.text = null
            }
        }
    }

    override fun onStop() {
        super.onStop()
        predictionViewModel.saveLastName()
    }
}