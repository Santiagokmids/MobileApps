package icesi.edu.co.firestoreejemplokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import icesi.edu.co.firestoreejemplokotlin.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener{
            val username = binding.usernameET.text.toString()
            val pass = binding.passET.text.toString()

            val user = User(UUID.randomUUID().toString(), username, pass)

            val query = Firebase.firestore.collection("users").whereEqualTo("username", username)

            query.get().addOnCompleteListener() { task ->
                //Obtengo respuesta

                //Si el usuario no existe lo creo
                if(task.result?.size() == 0){
                    Firebase.firestore.collection("users").document(user.id).set(user)

                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("user", user)
                    }
                    startActivity(intent)
                //si el usuario ya existe
                }else{
                    lateinit var existingUser: User

                    for (document in task.result!!){
                        existingUser = document.toObject(User::class.java)
                        break
                    }

                    if (existingUser.password == pass){
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            putExtra("user", existingUser)
                        }
                        startActivity(intent)

                    }else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}