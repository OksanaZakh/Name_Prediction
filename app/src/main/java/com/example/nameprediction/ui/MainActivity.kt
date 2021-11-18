package com.example.nameprediction.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nameprediction.databinding.ActivityMainBinding
import com.example.nameprediction.source.NetworkDataProvider
import com.example.nameprediction.source.PreferenceProvider
import com.example.nameprediction.source.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.checkNameBtn.setOnClickListener { startPredictionActivity() }


        val preferenceProvider = PreferenceProvider(this)
        val retrofitService = NetworkDataProvider.getInstance()
        val repository = Repository(retrofitService, preferenceProvider)
    }

    private fun startPredictionActivity() {
        val intent = Intent(this, PredictionActivity::class.java)
        startActivity(intent)
    }
}