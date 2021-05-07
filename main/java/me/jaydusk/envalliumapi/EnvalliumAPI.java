package me.jaydusk.envalliumapi;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnvalliumAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        API envalliumAPI = new API();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loading EnavlliumAPI...");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Loading API...");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"API Loaded...");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"EnvalliumAPI Loaded...");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
