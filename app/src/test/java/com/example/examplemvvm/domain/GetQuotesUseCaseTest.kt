package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetQuotesUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }


    @Test
    fun `when The Api Doesnt Return Anything Then Get Values From Database`() = runBlocking{
        // Given  (siem pre que hay una corrutina se poene coEvery)
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        // When
        getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }

    }





    @Test
    fun `when the api return something then get values from api`() = runBlocking {

        //Given
        val myList = listOf(Quote("esta es una cita", "Uriel"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList

        //When

        val response = getQuotesUseCase()


        //Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1){ quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }  // para verificar que  NO se llame a ese método, pongo 0
        assert(myList == response)
    }


}