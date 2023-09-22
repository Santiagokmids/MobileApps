package icesi.edu.co.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import icesi.edu.co.pokedex.databinding.ActivityMainBinding
import icesi.edu.co.pokedex.model.services.RetrofitServices
import icesi.edu.co.pokedex.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewmodel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewmodel.pokemon.observe(this){

            binding.pokemonName.text = it.name

            Glide.with(this@MainActivity)
                .load(it.sprites.front_default)
                .into(binding.pokemonImage)

            binding.pokemonAttack.text=it.stats[1].base_stat.toString()
            binding.pokemonSpeed.text=it.stats[5].base_stat.toString()
            binding.pokemonDefense.text=it.stats[2].base_stat.toString()
            binding.pokemonHp.text=it.stats[0].base_stat.toString()
        }

        binding.searchButton.setOnClickListener {
            viewmodel.searchPokemon(
                binding.pokemonInput.editText?.text.toString()
            )
        }

        binding.atraparBtn.setOnClickListener{
            viewmodel.catchPokemon()
        }

    }

}