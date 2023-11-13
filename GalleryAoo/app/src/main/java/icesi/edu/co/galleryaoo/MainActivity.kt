package icesi.edu.co.galleryaoo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import icesi.edu.co.galleryaoo.databinding.ActivityMainBinding
import icesi.edu.co.galleryaoo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val launcher = registerForActivityResult(StartActivityForResult(), ::onGaleryResult)

    val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.showUser()

        viewModel.userLD.observe(this){
            binding.textView.text = it.name
            binding.textView2.text = it.username
            Glide.with(this).load(it.urlImage).into(binding.profileImg)
        }

        binding.profileImg.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launcher.launch(intent)
        }
    }

    fun onGaleryResult(result: ActivityResult){
        val uri = result.data?.data
        Glide.with(this).load(uri).into(binding.profileImg)

        uri?.let {
            viewModel.uploadImage(it)
        }
        Log.e(">>>>", uri.toString())
    }
}