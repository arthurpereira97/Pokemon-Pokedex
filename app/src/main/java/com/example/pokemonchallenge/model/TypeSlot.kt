package com.example.pokemonchallenge.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeSlot(
    @SerializedName("slot")
    var slot : Int,
    @SerializedName("type")
    var type : Type,
): Parcelable