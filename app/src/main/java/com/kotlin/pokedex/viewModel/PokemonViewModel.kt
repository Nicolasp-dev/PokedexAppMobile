package com.kotlin.pokedex.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.pokedex.Constants
import com.kotlin.pokedex.api.PokemonService
import com.kotlin.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonViewModel: ViewModel() {

  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  private val service: PokemonService = retrofit.create(PokemonService::class.java)

  val pokemonInfo = MutableLiveData<PokemonResponse>()

  fun getPokemonData(id: Int){
    val call = service.getPokemon(id)

    call.enqueue(object : Callback<PokemonResponse> {
      override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
       if(response.isSuccessful){
         response.body()?.let { pokemon ->
           pokemonInfo.postValue(pokemon)
         }
       }else {
         when (response.code()) {
           400 -> {
             Log.e("Error 400", "Bad Connection")
           }
           404 -> {
             Log.e("Error 404", "Not found")
           }
           else -> {
             Log.e("Error", "Generic Error")
           }
         }
       }
      }

      override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
        Log.e("Error", t.message.toString())
        call.cancel()
      }

    })
  }



}