package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository){

    suspend operator fun invoke():Quote?{
        var quotes = repository.getAllQuotesFromDatabase()
        if (!quotes.isNullOrEmpty()){
            val  randomNumbre = (0..quotes.size -1).random()
            return quotes[randomNumbre]
        }
        return null
    }

}