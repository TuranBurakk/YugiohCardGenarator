package com.example.yugiohdeckgenarator.ui.deckScreen

import com.example.yugiohdeckgenarator.data.entity.CardData
import com.example.yugiohdeckgenarator.data.remote.Repository
import com.example.yugiohdeckgenarator.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeckUseCase @Inject constructor(
    private val repository: Repository
    ) {
    operator fun invoke(): Flow<Resource<CardData>> = flow {
        try {
            emit(Resource.loading())
            val card = repository.getCard()
            emit(Resource.success(card))
        }catch (e: HttpException){
            emit(Resource.error(e.localizedMessage))
        }catch (e : IOException){
            emit(Resource.error("Check your internet connect "))
        }
    }
}

