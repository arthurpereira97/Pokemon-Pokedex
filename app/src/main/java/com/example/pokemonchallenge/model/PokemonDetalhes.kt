package com.example.pokemonchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetalhes(
    @SerializedName("name")
    var name : String,
    @SerializedName("weight")
    var weight : Int,
    @SerializedName("id")
    var id : Int,
    @SerializedName("height")
    var height : Int,
    @SerializedName("types")
    var types : List<TypeSlot>,
    @SerializedName("sprites")
    var sprites : Sprite
): Parcelable