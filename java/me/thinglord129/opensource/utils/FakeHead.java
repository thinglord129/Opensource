package me.thinglord129.opensource.utils;

import net.minecraft.server.v1_8_R1.PacketPlayOutEntityEquipment;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeHead implements Listener {

    private static FakeHead instance;
    private Map<String, ItemStack> restoreMap;

    public FakeHead() {
        restoreMap = new HashMap<>();
    }

    public static FakeHead getInstance() {
        if (instance == null) instance = new FakeHead();
        return instance;
    }

    public void setHead(Player player, ItemStack stack){
        restoreMap.put(player.getName(), player.getEquipment().getHelmet());
        PacketPlayOutEntityEquipment equipmentPacket = new PacketPlayOutEntityEquipment(player.getEntityId(), 1, CraftItemStack.asNMSCopy(stack));
        sendPacket(player, equipmentPacket);
    }

    public void restoreHead(Player player){
        if (!restoreMap.containsKey(player.getName())) return;
        PacketPlayOutEntityEquipment equipmentPacket = new PacketPlayOutEntityEquipment(player.getEntityId(), 1, CraftItemStack.asNMSCopy(restoreMap.get(player.getName())));
        sendPacket(player, equipmentPacket);
        restoreMap.remove(player.getName());
    }

    private void sendPacket(Player sourcePlayer, PacketPlayOutEntityEquipment packet){
        for (Player player : getOnlinePlayers()){
            if (player != sourcePlayer){
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    private Set<Player> getOnlinePlayers(){
        Set<Player> players = new HashSet<>();
        for (World world : Bukkit.getWorlds()){
            if (!world.getPlayers().isEmpty()){
                players.addAll(world.getPlayers());
            }
        }
        return players;
    }

}
