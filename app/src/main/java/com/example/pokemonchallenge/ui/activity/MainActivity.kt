package com.example.pokemonchallenge.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonchallenge.R
import com.example.pokemonchallenge.databinding.ActivityMainBinding
import com.example.pokemonchallenge.ui.adapter.MainAdapter
import com.example.pokemonchallenge.ui.fragment.BusyDialogFragment
import com.example.pokemonchallenge.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }
    lateinit var viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(this)

        val busyDialog = BusyDialogFragment.show(supportFragmentManager)

        searchPokemonListener()

        updateListViewModel(busyDialog)
    }

    private fun updateListViewModel(busyDialog: BusyDialogFragment) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.pokemonResponseLiveData.observe(this, Observer
        { pokemonResponse ->
            pokemonResponse.let {
                binding.pokemonRecyclerView.adapter =
                    MainAdapter(pokemonResponse, this, this@MainActivity::openPokemonDetail)
                busyDialog.dismiss()
            }
        })
        viewModel.searchPokemons()
    }

    private fun searchPokemonListener() {
        binding.searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                openPokemonDetail(query)
                return false
            }

        })
    }

    private fun openPokemonDetail(pokemon: String) {
        PokemonDetailActivity.open(this, pokemon)
    }

}