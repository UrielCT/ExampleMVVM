package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase:GetQuotesUseCase,
    private val getRandomQuoteUseCase:GetRandomQuoteUseCase
) : ViewModel() {


    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()



    // hace la llamada al caso de uso para que devuelva las quotes y las almacene
    fun onCreate() {
        // corrutina
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuoteUseCase()

            if (!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    //hace una llamada al caso de uso para que devuelva una cita random
    fun randomQuote(){
        //corrutina
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote  = getRandomQuoteUseCase()
            if (quote!=null){
                quoteModel.postValue(quote)
            }
            isLoading.postValue(false)
        }
    }


}