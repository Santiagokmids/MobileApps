package icesi.edu.co.simplenavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import icesi.edu.co.simplenavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Puede funcionar el binding así:
    /*
    private lateinit var binding:ActivityMainBinding
    binding = ActivityMainBinding.inflate(layoutInflater)
     */

//------------------------------------------------------------------

    //XML -> View
    //Todas las variables que tiene la pantalla se van a inicializar
    //al mismo tiempo con el binding

    //De esta forma sólo se inicializa cuando se requiere y así se queda
    //No cambia
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.loginBtn.setOnClickListener{

            var email = binding.emailET.text.toString()
            var password = binding.passwordET.text.toString()

            var text = "${email} : ${password}"

            Toast.makeText(this, text, Toast.LENGTH_LONG).show()

            //Para pasar a otra actividad
            val intent = Intent(this, ProfileActivity::class.java)

            //Pasar datos a otra actividad
            intent.putExtra("email", email)

            startActivity(intent)
        }
    }
}