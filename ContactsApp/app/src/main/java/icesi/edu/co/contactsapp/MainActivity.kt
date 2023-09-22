package icesi.edu.co.contactsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import icesi.edu.co.contactsapp.adapter.ContactAdapter
import icesi.edu.co.contactsapp.databinding.ActivityMainBinding
import icesi.edu.co.contactsapp.model.Contact

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter : ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = ContactAdapter()

        binding.contactList.adapter = adapter
        binding.contactList.layoutManager = LinearLayoutManager(this)
        binding.contactList.setHasFixedSize(true)

        var launcher = registerForActivityResult(StartActivityForResult(), ::onResult)

        binding.addBtn.setOnClickListener{
            launcher.launch(Intent(this, NewContactsActivity::class.java))
        }
    }

    fun onResult(result: ActivityResult){

        if (result.resultCode == RESULT_OK){
            val name = result.data?.extras?.getString("name")
            val phone = result.data?.extras?.getString("phone")

            val contact = Contact(name!!, phone!!, 0)

            adapter.addContact(contact)
        }
    }
}