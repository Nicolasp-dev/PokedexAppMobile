package com.kotlin.pokedex.model

data class PokemonResponse(
    val name: String,
    val number: Int,
    val id: Int,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val types: List<TypeXX>
)