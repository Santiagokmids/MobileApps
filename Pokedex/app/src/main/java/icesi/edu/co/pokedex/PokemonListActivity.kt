package icesi.edu.co.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import icesi.edu.co.pokedex.databinding.ActivityPokemonListBinding
import icesi.edu.co.pokedex.model.dto.Pokemon
import icesi.edu.co.pokedex.viewmodel.PokemonListViewModel

class PokemonListActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityPokemonListBinding.inflate(layoutInflater)
    }

    private val viewModel : PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getUser("IDW")
        viewModel.pokemonList("IDW")
    }
}