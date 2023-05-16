package mask.lab.testkotlinguiforpractice

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin

class Test_Kotlin_GUI_for_practice : JavaPlugin(), Listener {
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val inventory = event.inventory
        if (inventory.holder is YourGUIHolder) {
            event.isCancelled = true
            val clickedItem = event.currentItem ?: return
            val player = event.whoClicked as? Player ?: return

            // プレイヤーがクリックしたアイテムの位置に基づいて処理を行う
            val slot = event.rawSlot
            when (slot) {
                // 特定のスロットに対する処理を記述する

                // 例: 特定のスロットをクリックした場合にGUIを閉じる
                8 -> {
                    player.closeInventory()
                }
            }
        }
    }

    class YourGUIHolder(private val size: Int) : InventoryHolder {
        override fun getInventory(): Inventory {
            return Bukkit.createInventory(this, size, "GUIのタイトル")
        }
    }

    fun createGUI(player: Player, size: Int) {
        val holder = YourGUIHolder(size)
        val inventory = holder.inventory

        // GUIにアイテムを配置する
        // 例: 各スロットに特定のアイテムを配置する
        val itemStack = ItemStack(Material.DIAMOND)
        for (i in 0 until size) {
            inventory.setItem(i, itemStack)
        }

        player.openInventory(inventory)
    }
}
