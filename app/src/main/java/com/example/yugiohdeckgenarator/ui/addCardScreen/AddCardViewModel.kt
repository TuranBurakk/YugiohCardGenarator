package com.example.yugiohdeckgenarator.ui.addCardScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugiohdeckgenarator.data.entity.Card
import com.example.yugiohdeckgenarator.data.entity.CardData
import com.example.yugiohdeckgenarator.ui.deckScreen.DeckUseCase
import com.example.yugiohdeckgenarator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val deckUseCase: DeckUseCase
): ViewModel() {
    private val _card = MutableLiveData<CardData?>()
    val card : LiveData<CardData?> = _card

    init {
        getCards()
    }

    fun getCards(){
        deckUseCase().onEach { result ->
            when (result.status) {
                Resource.Status.SUCCESS ->{
                    _card.value = result.data
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING ->{
                }
            }

        }.launchIn(viewModelScope)
    }

}