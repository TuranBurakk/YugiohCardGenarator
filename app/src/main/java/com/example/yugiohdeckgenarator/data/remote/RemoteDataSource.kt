package com.example.yugiohdeckgenarator.data.remote

import com.example.yugiohdeckgenarator.data.entity.CardData
import javax.inject.Inject

class RemoteDataSource
@Inject constructor(private val apiService: ApiService) {
    suspend fun getCard() : CardData{
    return apiService.getCard()
    }
}