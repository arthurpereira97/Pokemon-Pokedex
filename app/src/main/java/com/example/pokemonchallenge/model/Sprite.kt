package com.example.pokemonchallenge.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sprite(
    @SerializedName("back_default")
    var backDefault : String,
    @SerializedName("front_default")
    var frontDefault : String,
    @SerializedName("back_shiny")
    var backShiny : String,
    @SerializedName("front_shiny")
    var frontShiny : String,
): Parcelable