package com.example.pokemonchallenge.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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

        val pokemonNumber = intent.getStringExtra("pokemon")
        val busyDialog = BusyDialogFragment.show(supportFragmentManager)

        setupToolbar()

        updateDetailsViewModel(busyDialog, pokemonNumber)
    }

    private fun updateDetailsViewModel(
        busyDialog: BusyDialogFragment,
        pokemonNumber: String?,
    ) {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.detalheResponseLiveData.observe(this, Observer
        { pokemonResponse ->
            pokemonResponse.let {
                insertDetails(pokemonResponse)
                busyDialog.dismiss()
            }
        })
        viewModel.detailPokemon(pokemonNumber!!)
    }

    private fun setupToolbar() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    fun insertDetails(pokemonDetail : PokemonDetalhes) {
        setColor(pokemonDetail.types[0].type.name)
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

    fun setColor(color : String) {
        when (color) {
            "normal"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.normal))
            "fire"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.fire))
            "fighting"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.fighting))
            "water"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.water))
            "flying"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.flying))
            "grass"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.grass))
            "poison"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.poison))
            "electric"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.electric))
            "ground"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.ground))
            "psychic"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.psychic))
            "rock"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.rock))
            "ice"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.ice))
            "bug"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.bug))
            "dragon"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.dragon))
            "ghost"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.ghost))
            "dark"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.dark))
            "steel"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.steel))
            "fairy"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.fairy))
            "other"-> binding.fullScreen.setBackgroundColor(ContextCompat.getColor(this,R.color.other))
        }
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