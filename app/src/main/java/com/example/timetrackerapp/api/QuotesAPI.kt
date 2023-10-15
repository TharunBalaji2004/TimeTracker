package com.example.timetrackerapp.api

import com.example.timetrackerapp.models.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuotesAPI {
    @GET("/random")
    suspend fun getQuote(): Response<QuoteResponse>
}