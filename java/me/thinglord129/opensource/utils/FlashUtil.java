package me.thinglord129.opensource.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;

import java.util.HashSet;
import java.util.Set;

public class FlashUtil {

    private static FlashUtil instance;
    private Set<String> flashingPlayers;

    public FlashUtil() {
        flashingPlayers = new HashSet<>();
    }

    public static FlashUtil getInstance() {
        if (instance == null)
            instance = new FlashUtil();
        return instance;
    }

    public void addFlashing(Player player){
        if (!flashingPlayers.contains(player.getName())) flashingPlayers.add(player.getName());
    }

    public void removeFlashing(Player player){
        if (flashingPlayers.contains(player.getName())) flashingPlayers.remove(player.getName());
    }

    public void flashPlayers(){
        for (String string : flashingPlayers){
            Player player = Bukkit.getPlayer(string);
            if (player != null && player.isOnline()){
                flashPlayer(player);
            }
        }
    }

    public void flashPlayer(Player player){
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(entityPlayer, 1);
        for (Player sendToPlayer : player.getWorld().getPlayers()){
            if (sendToPlayer.canSee(player) && sendToPlayer != player) ((CraftPlayer) sendToPlayer).getHandle().playerConnection.sendPacket(animationPacket);
        }
    }
}
