package icesi.edu.co.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import icesi.edu.co.authentication.databinding.ActivitySingupBinding
import icesi.edu.co.authentication.model.User

class SingupActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySingupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ihaveacountTV.setOnClickListener {
            finish() //me devuelve a la main activity
        }

        binding.signupBtn.setOnClickListener(::register)
    }

    fun register(view:View){

        //1. Registrar user en bd
        Firebase.auth.createUserWithEmailAndPassword(
            binding.emailTI.editText?.text.toString(),
            binding.passTI.editText?.text.toString()

        ) .addOnSuccessListener {
            //2. Registar los datos del user en Firestore

            val id = Firebase.auth.currentUser?.uid

            val user = User(id!!,
                binding.nameTI.editText?.text.toString(),
                binding.emailTI.editText?.text.toString())

            Firebase.firestore.collection("users").document(id).set(user)
                .addOnSuccessListener {
                    sendVerificationEmail()
                    finish()
                }

        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }

    fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this, "Verifique su email antes de entrar", Toast.LENGTH_LONG).show()

        }?.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }
}