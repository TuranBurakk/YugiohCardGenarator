package com.example.yugiohdeckgenarator.data.remote

import com.example.yugiohdeckgenarator.data.entity.CardData
import com.example.yugiohdeckgenarator.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("cardinfo.php")
    suspend fun getCard() : CardData
}