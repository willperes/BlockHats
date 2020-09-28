package me.willperes.BlockHats.Command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlockHatsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            // Console
            sender.sendMessage(ChatColor.RED + "Console is not allowed to use this command.");
        }

        Player player = (Player) sender;

        // Plugin permission
        if(!player.hasPermission("blockhats.use")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if(label.equalsIgnoreCase("hat")) {

            ItemStack playerItem = player.getInventory().getItemInMainHand();

            // In case the amount of items that the player has in his hands is more than 1, the code won't allow that
            // the item is added to the player's head.
            if(playerItem.getAmount() > 1) {
                player.sendMessage(ChatColor.RED + "Please, hold only one item in your hand and type the command once again.");
                return true;
            }

            // If the player isn't holding any items, nothing will happen, except the error message xD.
            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                player.sendMessage(ChatColor.RED + "You're not holding any items in your hand.");
                return true;
            }

            // If the player already has something in his head, the items will be replaced.
            if(!(player.getInventory().getHelmet() == null)) {
                ItemStack currentHelmet = player.getInventory().getHelmet();
                player.getInventory().setHelmet(null);
                player.getInventory().setItemInMainHand(null);
                player.getInventory().setHelmet(playerItem);
                player.getInventory().setItemInMainHand(currentHelmet);
                player.sendMessage(ChatColor.YELLOW + "You added the item that was in your hand as your hat!");
                return true;
            }

            // Remove the item from the player's hand and will place it in the player's helmet slot.
            player.getInventory().setHelmet(playerItem);
            player.getInventory().setItemInMainHand(null);

            player.sendMessage(ChatColor.YELLOW + "You added the item that was in your hand as your hat!");

            return true;
        }

        return false;
    }
}
