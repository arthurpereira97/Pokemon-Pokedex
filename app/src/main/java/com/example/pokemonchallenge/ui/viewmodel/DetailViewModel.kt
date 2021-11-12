package com.example.pokemonchallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonchallenge.model.PokemonDetalhes
import com.example.pokemonchallenge.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel() {
    private val _detalheResponseLiveData = MutableLiveData<PokemonDetalhes>()

    val detalheResponseLiveData: LiveData<PokemonDetalhes>
        get() = _detalheResponseLiveData

    fun detailPokemon(parameter : String){
        if(_detalheResponseLiveData.value != null) return

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                PokemonRepository.getDetalhePokemon(parameter)
            }
            _detalheResponseLiveData.value = result!!
        }
    }
}