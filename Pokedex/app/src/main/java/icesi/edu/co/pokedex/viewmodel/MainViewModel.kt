package icesi.edu.co.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import icesi.edu.co.pokedex.model.dto.Pokemon
import icesi.edu.co.pokedex.model.entity.PokemonEntity
import icesi.edu.co.pokedex.model.services.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    //state: las variables que se muestran al user
    private val _pokemon=MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> get()=_pokemon
    //esta variable es observable
    //observer: vista
    //observable:_pokemon


    //eventos: recibe los eventos de la vista
    fun searchPokemon(searchTerm:String){

        viewModelScope.launch(Dispatchers.IO) {
            var call = RetrofitServices.pokedexRepository.getPokemon(
               searchTerm)
            var response = call.execute()
            var pokemon = response.body()
            //actualizar estado
            pokemon?.let {
                withContext(Dispatchers.Main) {
                    _pokemon.value=it
                }
            }
            Log.e(">>", pokemon.toString())
        }
    }

    fun catchPokemon(){

        viewModelScope.launch(Dispatchers.IO){

            _pokemon.value?.let {

                var newPokemon = PokemonEntity(
                    it.id.toString(),
                    it.name,
                    it.stats[1].base_stat,
                    it.stats[2].base_stat,
                    it.sprites.front_default
                )

                var call = RetrofitServices.fireBaseRepository.putPokemon(newPokemon.id, newPokemon)
                var response = call.execute()
            }
        }
    }
}