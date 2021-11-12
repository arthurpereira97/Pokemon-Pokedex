package com.example.pokemonchallenge.model
import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("name")
    var count : String,
    @SerializedName("next")
    var next : String,
    @SerializedName("previous")
    var previous : String,
    @SerializedName("results")
    var results : List<Pokemon>,
)
