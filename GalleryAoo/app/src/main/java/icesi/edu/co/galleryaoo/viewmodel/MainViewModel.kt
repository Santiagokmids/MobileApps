package icesi.edu.co.galleryaoo.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class MainViewModel: ViewModel() {

    val userLD = MutableLiveData<User>()

    fun uploadImage(uri:Uri){
        viewModelScope.launch(Dispatchers.IO) {
            //Cargar la imagen
            try {

                var uuid = UUID.randomUUID().toString()

                Firebase.storage.reference
                    .child("profileImages")//Cada child es una carpeta
                    .child("apps")
                    .child("232")
                    .child(uuid)//El último es el nombre del archivo
                    .putFile(uri).await()

                Firebase.firestore.collection("users")
                    .document("XIwWzOs8jzO9j5thrqwo6jMqX913")
                    .update("photoId",uuid).await()

            }catch (ex:Exception){
                Log.e(">>>", ex.message.toString())
            }

        }
    }

    fun showUser(){
        viewModelScope.launch(Dispatchers.IO) {

            var doc = Firebase.firestore.collection("users")
                .document("XIwWzOs8jzO9j5thrqwo6jMqX913").get().await()

            val user = doc.toObject(User::class.java)

            user?.let {
                val url = Firebase.storage.reference.child("profileImages")
                    .child("apps")
                    .child("232")
                    .child(it.photoId).downloadUrl.await()

                val localUser = User(
                    it.id,
                    it.photoId,
                    it.name,
                    it.username,
                    url.toString()
                )

                withContext(Dispatchers.Main){
                    userLD.value = localUser
                }
            }
        }

    }
}

data class User(
    var id : String = "",
    var photoId : String = "",
    var name : String = "",
    var username : String = "",
    var urlImage : String? = null //No necesariamente siempre esta presente
)
