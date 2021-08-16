package minerofmillions.factorio.recipe

import minerofmillions.factorio.EmptyJsonException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class Ingredient(
    val type: String,
    val name: String,
    val amount: Int,
    val probability: Float = 1f
) {
    object Serializer: JsonDeserializer<Ingredient> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Ingredient {
            val obj = json.asJsonObject
            if (!obj.has("type") || !obj.has("name")) throw EmptyJsonException("Ingredient has no name or no type.")
            val type = obj["type"].asString
            val name = obj["name"].asString
            val amount: Int = if (obj.has("amount")) {
                obj["amount"].asInt
            } else {
                val amountMin = obj["amount_min"].asInt
                val amountMax = obj["amount_max"].asInt
                (amountMin + amountMax) / 2
            }
            return if (obj.has("probability")) Ingredient(type, name, amount, obj["probability"].asFloat)
            else Ingredient(type, name, amount)
        }
    }

    override fun toString(): String = "$name * $amount @ ${probability * 100}%"
}
