package me.func.ebisu

import me.func.ebisu.listener.AcceptClick
import me.func.mod.conversation.ModLoader
import me.func.mod.util.listener
import me.func.mod.util.log
import org.bukkit.Location

class Ebisu {

    companion object {
        var lootboxes: Set<Location>? = null

        @JvmStatic
        fun run() {
            if (lootboxes == null) {
                println("Lootboxes location is null! Fill some with Ebisu#lootboxes()")
                return
            }
            listener(AcceptClick)
        }

        @JvmStatic
        fun lootboxes(vararg location: Location) = apply { lootboxes = location.toHashSet() }

        @JvmStatic
        fun lootboxes(locations: Iterable<Location>) = apply { lootboxes = locations.toHashSet() }
    }
}