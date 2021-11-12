package com.example.pokemonchallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonchallenge.R
import com.example.pokemonchallenge.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_pokemon.view.*

class MainAdapter(
    private var pList : List<Pokemon>,
    private var ctx : Context,
    private val onItemClick: (String) -> Unit
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.card_pokemon, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pokemon = pList[position]
        val pokeNumber = position + 1

        holder.itemView.pokemonName.text = pokemon.name
        Picasso.with(ctx).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeNumber}.png").into(holder.itemView.pokemonImage)
        holder.itemView.setOnClickListener {
            onItemClick(pokeNumber.toString())
        }
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.pokemonName)
        val image: ImageView = itemView.findViewById(R.id.pokemonImage)

    }

}