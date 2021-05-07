package me.jaydusk.envalliumapi.PlayerData;

import me.jaydusk.envalliumapi.EnvalliumAPI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class playerData {
    private static EnvalliumAPI main = JavaPlugin.getPlugin(EnvalliumAPI.class);

    private static File plc;
    private static FileConfiguration plcFile;

    public static void setup(Player player){


        plc = new File(main.getDataFolder(), "/playerdata/" + player.getName() + ".yml");

        if(!plc.exists()){
            try{
                plc.createNewFile();
            }catch(IOException e) {
                System.out.println(e);
            }
        }
        plcFile = YamlConfiguration.loadConfiguration(plc);
    }

    public static FileConfiguration get(Player player){
        plc = new File(main.getDataFolder(), "/playerdata/" + player.getName() + ".yml");
        plcFile = YamlConfiguration.loadConfiguration(plc);

        return plcFile;
    }

    public static void save(Player player){
        try {
            plc = new File(main.getDataFolder(), "/playerdata/" + player.getName() + ".yml");
            plcFile.save(plc);
        }catch(IOException e){
            System.out.println("Couldn't Save Player Data File");
        }
    }
}

