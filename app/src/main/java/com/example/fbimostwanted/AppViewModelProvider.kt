package com.example.fbimostwanted

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fbimostwanted.ui.HomeViewModel


object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(this.createSavedStateHandle())
        }
    }
}