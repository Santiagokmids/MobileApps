package icesi.edu.co.contactsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import icesi.edu.co.contactsapp.databinding.ActivityNewContactsBinding

class NewContactsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewContactsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener{
            val name = binding.nameET.text.toString()
            val phone = binding.phoneET.text.toString()

            val intent = Intent()

            intent.apply {
                putExtra("name", name)
                putExtra("phone", phone)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}