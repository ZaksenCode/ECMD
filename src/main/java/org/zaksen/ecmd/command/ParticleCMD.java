package org.zaksen.ecmd.command;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.zaksen.ecmd.ECMD;

public class ParticleCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        Location pLocation = player.getLocation().clone();
        new BukkitRunnable() {
            @Override
            public void run() {
                for(double x = -0.5; x <= 0.5; x += 0.1) {
                    for(double y = -0.5; y <= 0.5; y += 0.1) {
                        for(double z = -0.5; z <= 0.5; z += 0.1) {
                            if((Math.abs(x) == 0.5 && Math.abs(y) == 0.5) || (Math.abs(x) == 0.5 && Math.abs(z) == 0.5) || Math.abs(y) == 0.5 && Math.abs(z) == 0.5) {
                                Location location = new Location(
                                        pLocation.getWorld(),
                                        pLocation.getX() - 0.5 + x,
                                        pLocation.getY() - 0.5 + y,
                                        pLocation.getZ() - 0.5 + z
                                );

                                player.getWorld().spawnParticle(
                                        Particle.REDSTONE,
                                        location,
                                        1,
                                        new Particle.DustOptions(Color.RED, 1f)
                                );
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(ECMD.getInstance(), 0L, 20L);

        return true;
    }
}
