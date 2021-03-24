package com.example.customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            valueSelectionView.setOnValueChangeListener { cnt, maxValue ->
                val result = 360 * cnt / maxValue
                donutChartView.updateAngels(result.toFloat())


            }

            resetBtn.setOnClickListener {
                valueSelectionView.clearValue()
            }

        }

    }
}