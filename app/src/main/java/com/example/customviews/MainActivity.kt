package com.example.customviews

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mPreferences = this.getPreferences(Context.MODE_PRIVATE)

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