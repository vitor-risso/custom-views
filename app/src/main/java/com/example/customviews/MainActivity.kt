package com.example.customviews

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customviews.databinding.ActivityMainBinding
import com.example.customviews.ui.custom.MaxValueActivity

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

            valueSelectionView.maxValue = intent.getIntExtra("MAX_VALUE", 10)

            resetBtn.setOnClickListener {
                valueSelectionView.clearValue()
            }

            binding.setBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, MaxValueActivity::class.java))
            }

        }

    }

}