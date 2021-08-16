package minerofmillions.factorio.recipe

import minerofmillions.factorio.EmptyJsonException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import minerofmillions.factorio.DeserializerUtils
import java.lang.reflect.Type

data class Recipe(
    val name: String,
    @SerializedName("localised_name")
    val localisedName: List<String>,
    val category: String,
    val order: String,
    val group: Group,
    val subgroup: Group,
    val enabled: Boolean,
    @SerializedName("emissions_multiplier")
    val emissionsMultiplier: Float,
    val energy: Float,
    val ingredients: Set<Ingredient>,
    val products: Set<Ingredient>,
    @SerializedName("main_product")
    val mainProduct: Ingredient?
) {
    object Serializer : JsonDeserializer<Recipe> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Recipe {
            val obj = json.asJsonObject

            val name = obj["name"].asString
            val localisedName = DeserializerUtils.toLocalisedName(obj["localised_name"])
            val category = obj["category"].asString
            val order = obj["order"].asString
            val group = context.deserialize<Group>(obj["group"], Group::class.java)
            val subgroup = context.deserialize<Group>(obj["subgroup"], Group::class.java)
            val enabled = obj["enabled"].asBoolean
            val emissionsMultiplier = obj["emissions_multiplier"].asFloat
            val energy = obj["energy"].asFloat
            val ingredients = toIngredientList(obj["ingredients"], context).toSet()
            val products = toIngredientList(obj["products"], context).toSet()
            val mainProduct = context.deserialize<Ingredient>(obj["main_product"], Ingredient::class.java)

            return Recipe(
                name,
                localisedName,
                category,
                order,
                group,
                subgroup,
                enabled,
                emissionsMultiplier,
                energy,
                ingredients,
                products,
                mainProduct
            )
        }


        private fun toIngredientList(
            element: JsonElement,
            context: JsonDeserializationContext
        ): Collection<Ingredient> =
            if (element.isJsonArray) element.asJsonArray.mapNotNull {
                try {
                    context.deserialize<Ingredient>(it, Ingredient::class.java)
                } catch (e: EmptyJsonException) {
                    null
                }
            }
            else try {
                listOf(context.deserialize<Ingredient>(element, Ingredient::class.java))
            } catch (e: EmptyJsonException) {
                emptyList<Ingredient>()
            }
    }

    data class Group(val name: String, val type: String)

    override fun toString(): String = "$ingredients -> $products"
}
