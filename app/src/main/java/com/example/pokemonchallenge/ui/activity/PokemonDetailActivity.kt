package com.example.pokemonchallenge.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonchallenge.R
import com.example.pokemonchallenge.ui.viewmodel.DetailViewModel
import androidx.lifecycle.Observer
import com.example.pokemonchallenge.databinding.ActivityPokemonDetailBinding
import com.example.pokemonchallenge.model.PokemonDetalhes
import com.example.pokemonchallenge.ui.fragment.BusyDialogFragment
import com.squareup.picasso.Picasso

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel

    private val binding: ActivityPokemonDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityPokemonDetailBinding>(
            this,
            R.layout.activity_pokemon_detail
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var pokemonNumber = intent.getStringExtra("pokemon")

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        val busyDialog = BusyDialogFragment.show(supportFragmentManager)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.detalheResponseLiveData.observe(this, Observer
        { pokemonResponse ->
            insertDetails(pokemonResponse)
            busyDialog.dismiss()
        })
        viewModel.detailPokemon(pokemonNumber!!)
    }

    fun insertDetails(pokemonDetail : PokemonDetalhes) {
        Picasso.with(this).load(pokemonDetail.sprites.frontDefault).into(binding.pokemonImage1)
        Picasso.with(this).load(pokemonDetail.sprites.backDefault).into(binding.pokemonImage2)
        Picasso.with(this).load(pokemonDetail.sprites.frontShiny).into(binding.pokemonImage3)
        Picasso.with(this).load(pokemonDetail.sprites.backShiny).into(binding.pokemonImage4)
        binding.pokemonName.text = pokemonDetail.name
        binding.weight.text = pokemonDetail.weight.toString()
        binding.height.text = pokemonDetail.height.toString()

        var typeText = pokemonDetail.types[0].type.name
        if(pokemonDetail.types.size == 2) {
            typeText = typeText + " / " + pokemonDetail.types[1].type.name
        }

        binding.type.text = typeText
    }

    companion object {
        private const val POKEMON_NAME = "pokemon"
        fun open(context: Context, pokemon: String){
            val detailIntent = Intent(context,PokemonDetailActivity::class.java)
            detailIntent.putExtra(POKEMON_NAME, pokemon)
            context.startActivity(detailIntent)
        }
    }
}