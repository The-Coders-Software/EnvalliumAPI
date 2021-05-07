package me.jaydusk.envalliumapi;

import me.jaydusk.envalliumapi.PlayerData.playerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class API {


    //Player Data
    public static void initPlayerData(Player player){
        playerData.setup(player);
    }

    public static void createPlayerDataValue(Player player, String name, String defaultValue){
        playerData.get(player).set(name, defaultValue);
        playerData.save(player);
    }

    public static void createPlayerDataValue(Player player, String name, int defaultValue){
        playerData.get(player).set(name, defaultValue);
        playerData.save(player);
    }

    public static void createPlayerDataValue(Player player, String name, boolean defaultValue){
        playerData.get(player).set(name, defaultValue);
        playerData.save(player);
    }

    public static void editPlayerDataValue(Player player, String name, String newValue){
        playerData.get(player).set(name, newValue);
        playerData.save(player);
    }

    public static void editPlayerDataValue(Player player, String name, boolean newValue){
        playerData.get(player).set(name, newValue);
        playerData.save(player);
    }


    // HEX Color Translation
    private static String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.COLOR_CHAR + "x"
                    + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(0) + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(1)
                    + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(2) + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(3)
                    + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(4) + net.md_5.bungee.api.ChatColor.COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static String translateColor(String message){
        message = translateHexColorCodes("&#", "", message);
        message = ChatColor.translateAlternateColorCodes('&', message);

        return message;
    }


    // Player Leveling and EXP
    public static void InitLeveling(Player player, int expGap){
        playerData.get(player).set("Level", 1);
        playerData.save(player);
        playerData.get(player).set("Exp", 0);
        playerData.save(player);
        playerData.get(player).set("MaxExp", playerData.get(player).getInt("Level") * expGap);
        playerData.save(player);
        playerData.get(player).set("expGap", expGap);
        playerData.save(player);
    }

    public static void addExp(Player player, int expAmount){
        if(playerData.get(player).getInt("Exp") + expAmount > playerData.get(player).getInt("MaxExp")){
            int gap = (playerData.get(player).getInt("Exp") + expAmount) - playerData.get(player).getInt("MaxExp");
            playerData.get(player).set("Exp", gap);
            playerData.save(player);
            playerData.get(player).set("Level", playerData.get(player).getInt("Level") + 1);
            playerData.save(player);
        }else if(playerData.get(player).getInt("Exp") + expAmount == playerData.get(player).getInt("MaxExp")){
            playerData.get(player).set("Exp", 0);
            playerData.save(player);
            playerData.get(player).set("Level", playerData.get(player).getInt("Level") + 1);
            playerData.save(player);
        }else{
            int current = playerData.get(player).getInt("Exp");
            playerData.get(player).set("Exp", current + expAmount);
        }
    }

    public static void removeExp(Player player, int expAmount){
        if((playerData.get(player).getInt("Exp") - expAmount) < 0){
            int gap = 0 - (playerData.get(player).getInt("Exp") - expAmount);
            playerData.get(player).set("Exp", gap);
            playerData.save(player);
            playerData.get(player).set("Level", playerData.get(player).getInt("Level") - 1);
            playerData.save(player);
        }else if((playerData.get(player).getInt("Exp") - expAmount) == playerData.get(player).getInt("MaxExp")){
            playerData.get(player).set("Exp", 0);
            playerData.save(player);
            playerData.get(player).set("Level", playerData.get(player).getInt("Level") - 1);
            playerData.save(player);
        }else{
            int current = playerData.get(player).getInt("Exp");
            playerData.get(player).set("Exp", current - expAmount);
        }
    }

    public static void addLevel(Player player, int levelAmount){
        playerData.get(player).set("Level", playerData.get(player).getInt("Level") + levelAmount);
        playerData.save(player);
    }

}
