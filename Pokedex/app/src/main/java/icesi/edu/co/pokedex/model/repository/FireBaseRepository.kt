package icesi.edu.co.pokedex.model.repository

import icesi.edu.co.pokedex.model.dto.Pokemon
import icesi.edu.co.pokedex.model.dto.User
import icesi.edu.co.pokedex.model.entity.PokemonEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface FireBaseRepository {

    @PUT("users/IDW/pokemon/{id}.json")
    fun putPokemon(@Path("id") id : String, @Body pokemon: PokemonEntity) : Call<Any>

    @GET("users/{userId}/pokemon.json")
    fun getPokemons(@Path("userId") userId : String) : Call<HashMap<Int, PokemonEntity>>

    @GET("users/{id}.json")
    fun getUser(@Path ("id") id : String) : Call<User>
}