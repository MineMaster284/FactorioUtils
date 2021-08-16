package minerofmillions.factorio.recipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Furnace(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    val type: String,
    @SerializedName("energy_usage")
    val energyUsage: Int,
    @SerializedName("ingredient_count")
    val ingredientCount: Int,
    @SerializedName("crafting_speed")
    val craftingSpeed: Float,
    @SerializedName("crafting_categories")
    val craftingCategories: Map<String, Boolean>,
    @SerializedName("module_inventory_size")
    val moduleInventorySize: Int,
    @SerializedName("allowed_effects")
    val allowedEffects: Map<String, Boolean>,
    val drain: Float?,
    val emissions: Float?,
    val pollution: Float
) {
    object Serializer: JsonDeserializer<Furnace> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Furnace {
            val obj = json.asJsonObject
            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val type = obj["type"].asString
            val energyUsage = obj["energy_usage"].asInt
            val ingredientCount = obj["ingredient_count"].asInt
            val craftingSpeed = obj["crafting_speed"].asFloat
            val craftingCategories = context.deserialize<Map<String, Boolean>>(obj["crafting_categories"], DeserializerUtils.StringBooleanMapTypeToken.type)
            val moduleInventorySize = obj["module_inventory_size"].asInt
            val allowedEffects = context.deserialize<Map<String, Boolean>>(obj["allowed_effects"], DeserializerUtils.StringBooleanMapTypeToken.type)
            val drain = obj["drain"]?.asFloat
            val emissions = obj["emissions"]?.asFloat
            val pollution = obj["pollution"].asFloat
            return Furnace(name, localisedName, type, energyUsage, ingredientCount, craftingSpeed, craftingCategories, moduleInventorySize, allowedEffects, drain, emissions, pollution)
        }
    }
}
