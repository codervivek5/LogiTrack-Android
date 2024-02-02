package com.example.logitrackandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.logitrackandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}