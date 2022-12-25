package com.example.yugiohdeckgenarator.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardData(
    val data : List<Card>? = listOf()
):Parcelable

@Parcelize
data class Card(
    @SerializedName("id")val id : Long? = null,
    @SerializedName("name")val name : String? = null,
    @SerializedName("type")val type : String? = null,
    @SerializedName("desc")val desc : String? = null,
    @SerializedName("race")val race : String? = null,
    @SerializedName("archetype")val archetype : String? = null,
    @SerializedName("card_images")val cardImage : List<ImageData>? = listOf(),
):Parcelable

@Parcelize
data class ImageData(
    @SerializedName("image_url") val image : String? = null
):Parcelable

