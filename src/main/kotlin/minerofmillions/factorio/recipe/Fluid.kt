package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Fluid(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    val order: String,
    @SerializedName("default_temperature")
    val defaultTemperature: Int,
    @SerializedName("max_temperature")
    val maxTemperature: Int,
    @SerializedName("fuel_value")
    val fuelValue: Int,
    @SerializedName("emissions_multiplier")
    val emissionsMultiplier: Float
) {
    object Serializer: JsonDeserializer<Fluid> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Fluid {
            val obj = json.asJsonObject
            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val order = obj["order"].asString
            val defaultTemperature = obj["default_temperature"].asInt
            val maxTemperature = obj["max_temperature"].asInt
            val fuelValue = obj["fuel_value"].asInt
            val emissionsMultiplier = obj["emissions_multiplier"].asFloat
            return Fluid(name, localisedName, order, defaultTemperature, maxTemperature, fuelValue, emissionsMultiplier)
        }
    }
}
