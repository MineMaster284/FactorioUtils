package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Generator(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    @SerializedName("maximum_temperature")
    val maximumTemperature: Int,
    val effectivity: Float,
    @SerializedName("fluid_usage_per_tick")
    val fluidUsagePerTick: Float,
    val drain: Float,
    val emissions: Float,
    val pollution: Float,
    @SerializedName("max_energy_output")
    val maxEnergyOutput: Int?
) {
    object Serializer: JsonDeserializer<Generator> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Generator {
            val obj = json.asJsonObject

            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val maximumTemperature = obj["maximum_temperature"].asInt
            val effectivity = obj["effectivity"].asFloat
            val fluidUsagePerTick = obj["fluid_usage_per_tick"].asFloat
            val drain = obj["drain"].asFloat
            val emissions = obj["emissions"].asFloat
            val pollution = obj["pollution"].asFloat
            val maxEnergyOutput = obj["max_energy_output"]?.asInt

            return Generator(name, localisedName, maximumTemperature, effectivity, fluidUsagePerTick, drain, emissions, pollution, maxEnergyOutput)
        }
    }
}
