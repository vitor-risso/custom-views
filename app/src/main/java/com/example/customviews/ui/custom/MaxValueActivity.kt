package com.example.customviews.ui.custom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customviews.MainActivity
import com.example.customviews.databinding.ActivitySetmaxvalueBinding

class MaxValueActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetmaxvalueBinding;
    private lateinit var mPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetmaxvalueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreferences = this.getPreferences(Context.MODE_PRIVATE)

        with(binding) {

            set.setOnClickListener {
                with(mPreferences.edit()) {
                    putInt("MAX_VALUE", setText.text.toString().toInt())
                    apply()
                }
                val intent = Intent(this@MaxValueActivity, MainActivity::class.java)
                intent.putExtra("MAX_VALUE", mPreferences.getInt("MAX_VALUE", 1))
                intent.putExtra("NEED_TO_RESTART", true)

                startActivity(intent)
            }

            returnToMain.setOnClickListener {
                val intent = Intent(this@MaxValueActivity, MainActivity::class.java).apply {
                    putExtra("MAX_VALUE", mPreferences.getInt("MAX_VALUE", 1))
                }
                startActivity(intent)
            }
        }
    }
}