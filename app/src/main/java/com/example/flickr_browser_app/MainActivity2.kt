package com.example.flickr_browser_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.flickr_browser_app.databinding.ActivityMain2Binding
import com.example.flickr_browser_app.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initializeBinding()
        val title = intent.extras?.getString("title")
        val link = intent.extras?.getString("link")
        Glide.with(this).load(link).into(binding.m2imageView)

    }
    private fun initializeBinding() {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}