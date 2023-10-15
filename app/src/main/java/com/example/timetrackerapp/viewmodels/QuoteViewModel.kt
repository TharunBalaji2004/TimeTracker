package com.example.timetrackerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetrackerapp.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository): ViewModel() {

    val quotesLiveData = quoteRepository.quotesLiveData

    fun getQuote() {
        viewModelScope.launch {
            quoteRepository.getQuote()
        }
    }

}