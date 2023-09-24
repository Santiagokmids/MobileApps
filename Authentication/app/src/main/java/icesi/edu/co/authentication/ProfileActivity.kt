package icesi.edu.co.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import icesi.edu.co.authentication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.changepassBtn.setOnClickListener(::changePassword)
    }

    fun changePassword(view: View){
        val newPass = binding.newpassET.editText?.text.toString()

        if(newPass.isEmpty()){
            binding.newpassET.error = "El campo no puede estar vacío"
            return

        }else{
            binding.newpassET.error = null
        }

        if (newPass.length < 6){
            binding.newpassET.error = "La contraseña necesita mínimo 6 caracteres"
            return

        }else{
            binding.newpassET.error = null
        }

        val builder = AlertDialog.Builder(this)
            .setTitle("Cambio de contraseña")
            .setMessage("¿Desea cambiar la contraseña?")
            .setPositiveButton("Sí"){ dialog, _ ->
                Firebase.auth.currentUser?.updatePassword(newPass)?.addOnSuccessListener {
                    Toast.makeText(this, "Contraseña cambiada", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                    finish()

                }?.addOnFailureListener{
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }

            }.setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }

        builder.show()
    }
}