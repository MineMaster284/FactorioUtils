package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Item(
    val name: String,
    @SerializedName("localised_name")
    val localizedName: List<String>,
    val type: String,
    val order: String,
    @SerializedName("fuel_value")
    val fuelValue: Float,
    @SerializedName("stack_size")
    val stackSize: Int,
    @SerializedName("place_result")
    val placeResult: String?
) {
    object Serializer: JsonDeserializer<Item> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Item {
            val obj = json.asJsonObject

            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val type = obj["type"].asString
            val order = obj["order"].asString
            val fuelValue = obj["fuel_value"].asFloat
            val stackSize = obj["stack_size"].asInt
            val placeResult = obj["place_result"]?.asString

            return Item(name, localisedName, type, order, fuelValue, stackSize, placeResult)
        }
    }
}
