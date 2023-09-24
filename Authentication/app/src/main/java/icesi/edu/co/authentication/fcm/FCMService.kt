package icesi.edu.co.authentication.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import icesi.edu.co.authentication.model.Message
import icesi.edu.co.authentication.util.NotificationUtil
import org.json.JSONObject

class FCMService : FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        val obj = JSONObject(message.data as Map<*, *>)
        val json = obj.toString()
        val message = Gson().fromJson(json, Message::class.java)
        NotificationUtil.showNotification(this, "Mensaje nuevo", message.message)
    }
}