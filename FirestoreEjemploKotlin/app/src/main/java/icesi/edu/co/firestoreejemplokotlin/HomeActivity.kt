package icesi.edu.co.firestoreejemplokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import icesi.edu.co.firestoreejemplokotlin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ArrayAdapter<User>
    private lateinit var users: ArrayList<User>
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        user = intent.extras?.get("user") as User
        users = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)

        binding.userListView.adapter = adapter

        Firebase.firestore.collection("users").get().addOnCompleteListener{ task ->

            for (document in task.result!!){
                val user = document.toObject(User::class.java)
                Log.e(">>>", user.username)
                users.add(user)
                adapter.notifyDataSetChanged()
            }

        }

        binding.userListView.setOnItemClickListener { parent, view, position, id ->
            val contact = users[position]

            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("contact", contact)
                putExtra("user", user)
            }
            startActivity(intent)
        }
    }
}