package minerofmillions.factorio

import com.google.gson.JsonParseException

class EmptyJsonException(msg: String): JsonParseException(msg) {
}
