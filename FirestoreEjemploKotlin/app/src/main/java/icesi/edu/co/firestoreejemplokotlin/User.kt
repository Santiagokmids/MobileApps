package icesi.edu.co.firestoreejemplokotlin

import java.io.Serializable

data class User (

    var id:String = "",
    var username:String = "",
    var password:String = ""
) : Serializable {
    override fun toString(): String {
        return username
    }
}


