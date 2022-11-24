package com.kotlin.pokedex.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kotlin.pokedex.R
import com.kotlin.pokedex.databinding.MainActivityBinding
import com.kotlin.pokedex.viewModel.PokemonViewModel

class MainActivity : AppCompatActivity() {

  private lateinit var binding: MainActivityBinding
  private lateinit var viewModel: PokemonViewModel
  //private lateinit var ivPokemon: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)
    //ivPokemon = findViewById(R.id.iv_pokemon)

    viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

    initUi()

    binding.btnSubmit.setOnClickListener{
      val id = binding.tiNumber.text.toString().toInt()
      initUi(id)
    }
  }

  @SuppressLint("SetTextI18n")
  private fun initUi(id: Int = 0){
    viewModel.getPokemonData(id)

    viewModel.pokemonInfo.observe(this, Observer{
      pokemon ->
        binding.tvName.text = pokemon.name.capitalize()
        binding.tvNumber.text = "N° ${pokemon.id.toString().padStart(3,'0')}"
      Glide
        .with(this@MainActivity)
        .load(pokemon.sprites.front_default)
        .into(binding.imageView);
    })

    binding.tiNumber.setText("")
  }

}