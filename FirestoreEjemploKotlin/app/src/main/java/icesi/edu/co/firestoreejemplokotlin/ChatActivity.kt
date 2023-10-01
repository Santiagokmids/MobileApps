package icesi.edu.co.firestoreejemplokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import icesi.edu.co.firestoreejemplokotlin.databinding.ActivityChatBinding
import java.util.Date
import java.util.UUID

class ChatActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var contact: User

    private lateinit var chat: Chat

    private val binding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        user = intent.extras?.get("user") as User
        contact = intent.extras?.get("contact") as User

        Firebase.firestore.collection("users").document(user.id).collection("chats")
            .whereEqualTo("contactId", contact.id).get().addOnCompleteListener{task ->

                if(task.result?.size() == 0){
                    createChat()

                }else{
                   for (document in task.result){
                       chat = document.toObject(Chat::class.java)
                       break
                   }
                }
                //Podemos leer los mensajes de ambos usuarios
                getMessages()
            }

        binding.sendBtn.setOnClickListener{
            val message = Message(UUID.randomUUID().toString(), binding.messageET.text.toString(), user.id, Date().time)

            Firebase.firestore.collection("chats").document(chat.id).collection("messages").document(message.id).set(message)
        }
    }

    private fun createChat(){
        val id = UUID.randomUUID().toString()

        //Se crea el chat hacia el usuario al que le di click
        chat = Chat(id, contact.id)

        //Se crea el chat desde el usuario al que le dieron click hasta el qee dio click
        val foreignChat = Chat(id, user.id)

        Firebase.firestore.collection("users").document(user.id).collection("chats").document(chat.id).set(chat)
        Firebase.firestore.collection("users").document(contact.id).collection("chats").document(chat.id).set(foreignChat)

    }

    private fun getMessages(){
        Firebase.firestore.collection("chats").document(chat.id).collection("messages").addSnapshotListener{value, error ->

            binding.messagesTV.setText("")

            for (document in value!!.documents){
                val message = document.toObject(Message::class.java)
                binding.messagesTV.append(message?.message + "\n\n")
            }
        }
    }
}