package com.example.fbimostwanted.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.fbimostwanted.WantedList
import com.example.fbimostwanted.net.RemoteWantedSource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {

    val wantedSource = RemoteWantedSource()

    @OptIn(SavedStateHandleSaveableApi::class)
    var page: Int by savedStateHandle.saveable {
        mutableIntStateOf(1)
    }



    private val _state = mutableStateOf(WantedList(listOf()))
    val state: State<WantedList> = _state


    init {
        viewModelScope.launch {
            _state.value = wantedSource.getWantedPage(page)
        }
    }

    fun advancePage(){
        viewModelScope.launch {
            _state.value = wantedSource.getWantedPage(page++)
        }
    }
    fun decreasePage(){
        if (page >1 ){
            viewModelScope.launch {
                _state.value = wantedSource.getWantedPage(page--)
            }
        }
    }
}