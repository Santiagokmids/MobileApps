package icesi.edu.co.authentication.fcm

data class FCMMessage<T:Any>(
    var to: String = "",
    var data : T? = null
)