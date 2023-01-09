package com.example.yugiohdeckgenarator.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val cardList : List<CardListIn>? = null,
    val myCardList: List<myCard>? = null
):Parcelable

@Parcelize
data class myCard(
    val desc : String? = null,
    val cardName: String? = null,
    val myImage : String? = null,
    val atk : String? = null,
    val def : String? = null
):Parcelable

@Parcelize
data class FireCardData(
    val image : String? = null
):Parcelable

@Parcelize
data class CardListIn(
    val listCard : List<Card>? = null,
    val name : String? = null
):Parcelable
