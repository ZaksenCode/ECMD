package org.zaksen.ecmd.command;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.zaksen.ecmd.ECMD;

public class SinParticle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        Location pLocation = player.getLocation().clone();
        PerlinNoiseGenerator noise = PerlinNoiseGenerator.getInstance();
        new BukkitRunnable() {
            @Override
            public void run() {
                for(double x = -3; x <= 3; x += 0.1) {
                    for (double z = -3; z <= 3; z += 0.1) {
                        double height = noise.noise(x, z);
                        Location cosLocation = new Location(
                                pLocation.getWorld(),
                                pLocation.getX() - 3 + x,
                                pLocation.getY() + height,
                                pLocation.getZ() - 3 + z
                        );

                        player.getWorld().spawnParticle(
                                Particle.REDSTONE,
                                cosLocation,
                                1,
                                new Particle.DustOptions(getColorByHeight(height), 1f)
                        );
                    }
                }
            }
        }.runTaskTimer(ECMD.getInstance(), 0L, 5L);

        return true;
    }

    private static Color getColorByHeight(double height) {
        if(height > -0.5 && height < -0.3) {
            return Color.AQUA;
        } else if (height < -0.1) {
            return Color.YELLOW;
        } else if (height < 0.3) {
            return Color.GREEN;
        } else if (height < 0.5) {
            return Color.GRAY;
        } else if (height < 1) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
