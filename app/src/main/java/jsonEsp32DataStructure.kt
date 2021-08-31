import com.google.gson.JsonObject

data class jColor(
    var r : Int ,
    var g : Int ,
    var b : Int
)
data class jConfig(
    var mode : Int ,
    var selected : Int ,
    var color : jColor ,
    var time : Int
)

data class jEffect(
    var name : String,
    var editable : Int,  //ciekawa sprawa !! editable intem bo esp boola robi 1/0 i przez to sie wywala
    var data  : JsonObject
)

data class jAllData(
    var config : jConfig ,
    var effects : List<jEffect>
)

class jsonEsp32DataStructure {
}