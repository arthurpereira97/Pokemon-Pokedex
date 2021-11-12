package com.example.pokemonchallenge.ui.viewmodel

import com.example.pokemonchallenge.model.Pokemon
import com.example.pokemonchallenge.repositories.PokemonRepository
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : ViewModel() {
    private val _pokemonResponseLiveData = MutableLiveData<List<Pokemon>>()

    val pokemonResponseLiveData: LiveData<List<Pokemon>>
        get() = _pokemonResponseLiveData

    fun searchPokemons(){
        if(_pokemonResponseLiveData.value != null) return

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                PokemonRepository.getAllPokemons()
            }
            _pokemonResponseLiveData.value = result?.results
        }
    }

}