package icesi.edu.co.fragmentsintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import icesi.edu.co.fragmentsintro.databinding.ActivityMainBinding
import icesi.edu.co.fragmentsintro.fragments.AFragment
import icesi.edu.co.fragmentsintro.fragments.BFragment
import icesi.edu.co.fragmentsintro.model.TaskRepository

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val fragmentA = AFragment.newInstance()
    private val fragmentB = BFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showFragment(fragmentA)

        binding.navbar.setOnItemSelectedListener {

            when(it.itemId){
                R.id.aMenu ->{
                    showFragment(fragmentA)
                }
                R.id.bMenu ->{
                    showFragment(fragmentB)
                }
            }
            true
        }

        TaskRepository.taskLiveData.observe(this){

            if (TaskRepository.taskLiveData.value!!.isNotEmpty()){
                Toast.makeText(this, it.last().description, Toast.LENGTH_LONG).show()
            }
        }
    }

    //Así cargamos un fragmento en tiempo de ejecución
    fun showFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}