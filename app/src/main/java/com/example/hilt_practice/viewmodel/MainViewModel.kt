package com.example.hilt_practice.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.hilt_practice.network.model.BlogData
import com.example.hilt_practice.repository.Repository

class MainViewModel
@ViewModelInject
constructor(
        private var _repository : Repository
    ): ViewModel() {

    private lateinit var _mediatorLiveData : MediatorLiveData<List<BlogData>>

    fun getLiveData() : MediatorLiveData<List<BlogData>>{
        loadData()
        return _mediatorLiveData
    }

    fun loadData(){
       _mediatorLiveData = _repository.fetchData()
    }
}