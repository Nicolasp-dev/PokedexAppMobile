package com.kotlin.pokedex.api

import com.kotlin.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
  @GET("pokemon/{id}")
  fun getPokemon(
    @Path("id") id: Int
  ): Call<PokemonResponse>
}