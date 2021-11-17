package com.example.nameprediction.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nameprediction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.checkNameBtn.setOnClickListener { startPredictionActivity() }
    }

    private fun startPredictionActivity() {
        val intent = Intent(this, PredictionActivity::class.java)
        startActivity(intent)
    }
}