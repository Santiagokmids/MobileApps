package icesi.edu.co.simplenavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import icesi.edu.co.simplenavigation.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val email:String? = intent.extras?.getString("email")

        //String? -> String

        email?.let {
            binding.emailTV.text = it
        }

        //Launcher
        //Se usa para recibir de otras actividades

        var launcher = registerForActivityResult(StartActivityForResult(), ::onResult)

        binding.sendEditBtn.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            launcher.launch(intent)
        }

    }

    fun onResult(result: ActivityResult){

        if(result.resultCode == RESULT_OK){
            val img = result.data?.extras?.getInt("img")
            val name = result.data?.extras?.getString("name")

            img?.let {
                binding.profileImg.setImageResource(it)
            }

            binding.nameTV.text = name
        }
    }
}