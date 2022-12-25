package com.example.yugiohdeckgenarator.data.remote

import com.example.yugiohdeckgenarator.data.entity.CardData
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
)  {
    suspend fun getCard() : CardData {
     return remoteDataSource.getCard()
    }
}