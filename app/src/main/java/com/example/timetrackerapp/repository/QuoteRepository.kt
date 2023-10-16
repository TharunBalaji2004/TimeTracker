package com.example.timetrackerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetrackerapp.api.QuotesAPI
import com.example.timetrackerapp.models.QuoteResponse
import com.example.timetrackerapp.utils.NetworkResult
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quotesAPI: QuotesAPI) {

    private val _quotesLiveData = MutableLiveData<NetworkResult<QuoteResponse>>()
    val quotesLiveData: LiveData<NetworkResult<QuoteResponse>> = _quotesLiveData

    suspend fun getQuote() {
        _quotesLiveData.postValue(NetworkResult.Loading())
        try {
            val response = quotesAPI.getQuote()

            if (response.isSuccessful && response.body() != null) {
                _quotesLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else {
                _quotesLiveData.postValue(NetworkResult.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            _quotesLiveData.postValue(NetworkResult.Error(e.message))
        }
    }
}