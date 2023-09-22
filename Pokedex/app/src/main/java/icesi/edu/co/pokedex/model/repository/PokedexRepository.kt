package icesi.edu.co.pokedex.model.repository

import icesi.edu.co.pokedex.model.dto.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexRepository {

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: String) : Call<Pokemon>
}