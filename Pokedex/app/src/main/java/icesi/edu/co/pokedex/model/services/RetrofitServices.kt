package icesi.edu.co.pokedex.model.services

import icesi.edu.co.pokedex.model.repository.FireBaseRepository
import icesi.edu.co.pokedex.model.repository.PokedexRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitServices {

    var pokedexService= Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var pokedexRepository =pokedexService
        .create(PokedexRepository::class.java)

    var fireBaseService = Retrofit.Builder()
        .baseUrl("https://appsmobiles-c230f-default-rtdb.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var fireBaseRepository = fireBaseService
        .create(FireBaseRepository::class.java)

}