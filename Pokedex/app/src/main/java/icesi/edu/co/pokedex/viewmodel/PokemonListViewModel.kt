package icesi.edu.co.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import icesi.edu.co.pokedex.model.dto.User
import icesi.edu.co.pokedex.model.services.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> get() = _user

    fun getUser(idUser : String){

        viewModelScope.launch(Dispatchers.IO) {
            var call = RetrofitServices.fireBaseRepository.getUser(
                idUser)
            var response = call.execute()
            var user = response.body()

            //actualizar estado
            user?.let {
                withContext(Dispatchers.Main) {
                    Log.e(">>", user.name)

                    it.pokemon.values.forEach{
                        Log.e(">>", it.name)
                    }
                }
            }
        }
    }

    fun pokemonList(userId : String){

        viewModelScope.launch(Dispatchers.IO) {
            var call = RetrofitServices.fireBaseRepository.getPokemons(
                userId)
            var response = call.execute()

            Log.e(">>", response.body().toString())
        }
    }
}