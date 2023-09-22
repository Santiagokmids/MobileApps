package icesi.edu.co.pokedex.model.dto

data class Pokemon (
    var id:Int,
    var name:String,
    var sprites:Sprites,
    var stats:ArrayList<StatWrapper>
        )

data class Sprites(
    var front_default:String
)

data class StatWrapper(
    var base_stat:Int,
    var effort:Int,
    var stat:Stat
)
data class Stat(
    var name: String,
    var url:String
)