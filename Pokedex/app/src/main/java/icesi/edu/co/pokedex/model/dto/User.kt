package icesi.edu.co.pokedex.model.dto

import icesi.edu.co.pokedex.model.entity.PokemonEntity

data class User(

    var id : String,
    var name : String,
    var pokemon : HashMap<Int, PokemonEntity>
)
