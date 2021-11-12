package com.example.pokemonchallenge.repositories

import com.example.pokemonchallenge.model.PokemonDetalhes
import com.example.pokemonchallenge.model.PokemonList
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit


object PokemonRepository {

    private val POKEMON_SEARCH_SERVICE_BASE_URL = "https://pokeapi.co/api/v2/"
    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun getAllPokemons(): PokemonList? {
        val request = Request.Builder()
            .url(String.format(POKEMON_SEARCH_SERVICE_BASE_URL + "pokemon?limit=151"))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body()?.string()
            return Gson().fromJson(json, PokemonList::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getDetalhePokemon(parameter : String): PokemonDetalhes? {
        val request = Request.Builder()
            .url(String.format(POKEMON_SEARCH_SERVICE_BASE_URL + "pokemon/" + parameter))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body()?.string()
            return Gson().fromJson(json, PokemonDetalhes::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}