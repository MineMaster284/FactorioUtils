package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class TransportBelt(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    @SerializedName("belt_speed")
    val beltSpeed: Float,
    val pollution: Float
) {
    object Serializer: JsonDeserializer<TransportBelt> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): TransportBelt {
            val obj = json.asJsonObject

            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val beltSpeed = obj["belt_speed"].asFloat
            val pollution = obj["pollution"].asFloat

            return TransportBelt(name, localisedName, beltSpeed, pollution)
        }
    }
}
