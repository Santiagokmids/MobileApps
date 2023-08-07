package icesi.edu.co.simplenavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import icesi.edu.co.simplenavigation.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.femaleImg.setOnClickListener{
            binding.checkFemale.visibility = View.VISIBLE
            binding.checkMale.visibility = View.GONE
        }

        binding.maleImg.setOnClickListener{
            binding.checkFemale.visibility = View.GONE
            binding.checkMale.visibility = View.VISIBLE
        }

        binding.okBtn.setOnClickListener{

            var profileImg:Int = R.drawable.ic_launcher_background

            if(binding.checkFemale.visibility == View.VISIBLE){
                profileImg = R.drawable.mujer

            }else if (binding.checkMale.visibility == View.VISIBLE){
                profileImg = R.drawable.hombrearana
            }

            var name = binding.editNameET.text.toString()

            val intent = Intent()

            intent.putExtra("img", profileImg)
            intent.putExtra("name", name)

            setResult(RESULT_OK, intent)

            finish()
        }
    }
}