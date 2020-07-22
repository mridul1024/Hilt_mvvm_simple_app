package com.example.hilt_practice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hilt_practice.R
import com.example.hilt_practice.repository.Repository
import com.example.hilt_practice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val TAG : String = "MainActivity "

    lateinit var _repository : Repository
    private val _viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: calling fetch data")
        _viewModel.getLiveData().observe(this, Observer {
            textView.text = it.get(1).body
        })
    }
}