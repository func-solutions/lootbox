package me.func.ebisu.listener

import me.func.ebisu.Ebisu
import me.func.mod.menu.button
import me.func.mod.menu.choicer
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object AcceptClick : Listener {

    private val menu = choicer {
        title = "Меню лутбоксов"
        description = "Выбивайте самые крутые награды: граффити, шапки, крылья, статуэтки..."
        buttons(
            button {
                title = "История"
                description = "Последние\nвыпадения"
                hint = "Смотреть"
                command = "box-logs"
                texture = "minecraft:mcpatcher/cit/others/hub/quest_month.png"
            },
            button {
                special = true
                title = "Кейсы"
                description = "Открыть\nнаборы"
                hint = "Смотреть"
                command = "box-list"
                texture = "minecraft:mcpatcher/cit/others/hub/new_lvl_rare_close.png"
            },
            button {
                title = "Награды"
                description = "Что может\nвыпасть?"
                hint = "Смотреть"
                command = "box-rewards"
                texture = "minecraft:mcpatcher/cit/others/hub/stock.png"
            },
        )
    }

    @EventHandler
    fun PlayerInteractEvent.handle() {
        if (blockClicked == null)
            return
        val type = blockClicked.type
        if (type != Material.CHEST && type != Material.ENDER_CHEST)
            return
        if (!Ebisu.lootboxes!!.contains(blockClicked.location))
            return
        isCancelled = true
        menu.open(player)
    }

}