package minerofmillions.factorio.recipe

import com.google.gson.annotations.SerializedName
import java.util.*

class RecipeListerOutput(
    val recipe: SortedMap<String, Recipe>,
    val item: SortedMap<String, Item>,
    val fluid: SortedMap<String, Fluid>,
    @SerializedName("assembling-machine")
    val assemblingMachine: SortedMap<String, AssemblingMachine>,
    val furnace: SortedMap<String, Furnace>,
    @SerializedName("transport-belt")
    val transportBelt: SortedMap<String, TransportBelt>,
    val boiler: SortedMap<String, Boiler>,
    val generator: SortedMap<String, Generator>,
    val reactor: SortedMap<String, Reactor>
)
