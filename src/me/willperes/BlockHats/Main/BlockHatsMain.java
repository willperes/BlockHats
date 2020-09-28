package me.willperes.BlockHats.Main;

import me.willperes.BlockHats.Command.BlockHatsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockHatsMain extends JavaPlugin {

    public void onEnable() {

        this.getCommand("hat").setExecutor(new BlockHatsCommand());

    }

    public void onDisable() {

    }

}
