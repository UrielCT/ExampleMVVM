package com.example.examplemvvm.data.network

import com.example.examplemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:QuoteApiClient){


    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response:Response<List<QuoteModel>> = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}