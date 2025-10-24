package com.example.opennetinterview.viewmodel

import androidx.lifecycle.ViewModel
import com.example.opennetinterview.repo.MainRepository

class MainViewModel(private val repo: MainRepository): ViewModel() {

    fun clearData() {
        repo.clearData()
    }
}