package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Boiler(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    @SerializedName("max_energy_usage")
    val maxEnergyUsage: Int,
    @SerializedName("target_temperature")
    val targetTemperature: Int,
    @SerializedName("burner_effectivity")
    val burnerEffectivity: Float?,
    @SerializedName("fuel_categories")
    val fuelCategories: Map<String, Boolean>?,
    val emissions: Float?,
    val pollution: Float
) {
    object Serializer: JsonDeserializer<Boiler> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boiler {
            val obj = json.asJsonObject
            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val maxEnergyUsage = obj["max_energy_usage"].asInt
            val targetTemperature = obj["target_temperature"].asInt
            val burnerEffectivity = obj["burner_effectivity"]?.asFloat
            val fuelCategories = context.deserialize<Map<String, Boolean>>(obj["fuel_categories"], DeserializerUtils.NullableStringBooleanMapTypeToken.type)
            val emissions = obj["emissions"]?.asFloat
            val pollution = obj["pollution"].asFloat
            return Boiler(name, localisedName, maxEnergyUsage, targetTemperature, burnerEffectivity, fuelCategories, emissions, pollution)
        }
    }
}
