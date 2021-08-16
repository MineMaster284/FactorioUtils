package minerofmillions.factorio

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DeserializerUtils: JsonDeserializer<List<String>> {
    fun toLocalisedName(element: JsonElement) = toListOfStrings(element).filter { it.isNotBlank() }

    private fun toListOfStrings(element: JsonElement): Collection<String> =
        if (element.isJsonPrimitive && element.asJsonPrimitive.isString) listOf(element.asString)
        else element.asJsonArray.flatMap(this::toListOfStrings)

    object StringBooleanMapTypeToken: TypeToken<Map<String, Boolean>>() {}
    object NullableStringBooleanMapTypeToken: TypeToken<Map<String, Boolean>?>() {}

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<String> =
        toLocalisedName(json)
}
