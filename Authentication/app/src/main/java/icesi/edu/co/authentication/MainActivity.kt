package icesi.edu.co.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import icesi.edu.co.authentication.databinding.ActivityMainBinding
import icesi.edu.co.authentication.model.User
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.noAccount.setOnClickListener {
            startActivity(Intent(this, SingupActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()

            Firebase.auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {

                val fbuser = Firebase.auth.currentUser

                if (fbuser!!.isEmailVerified){
                    //Le damos acceso

                    //1. Pedir el user almacenado en firebase
                    Firebase.firestore.collection("users").document(fbuser.uid).get()
                        .addOnSuccessListener {
                            val user = it.toObject(User::class.java)

                            //2. Guardar el usuario en las SP
                            saveUser(user!!)
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }
                }else{
                    Toast.makeText(this, "Email no verificado", Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener{
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }

        binding.forgotPassTV.setOnClickListener {
            Firebase.auth.sendPasswordResetEmail(binding.emailET.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "Revise su correo "+binding.emailET.text.toString(), Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun saveUser(user : User){

        val sp = getSharedPreferences("Authentication", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("user", json).apply()
    }
}