package com.example.nameprediction.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nameprediction.databinding.ActivityPredictionBinding
import com.example.nameprediction.model.Repository
import com.example.nameprediction.model.RetrofitService
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

        val retrofitService = RetrofitService.getInstance()
        val repository = Repository(retrofitService)

        viewModel = ViewModelProvider(this, PredictionViewModelFactory(repository))
            .get(PredictionViewModel::class.java)

        viewModel.person.observe(this, {
            if (it != null) binding.prediction.text = it.toString()
        })

        viewModel.isLoading.observe(
            this,
            {
                if (it) binding.progressBar.visibility =
                    View.VISIBLE else binding.progressBar.visibility = View.GONE
            })

        viewModel.snackBar.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.onSnackBarShown()
            }
        })

        binding.predictButton.setOnClickListener {
            val userInput = binding.userInput.text?.toString()
            userInput?.let {
                viewModel.onGetDataButtonClicked(it)
            }
        }
    }
}