package org.zaksen.ecmd;

import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.ecmd.command.ParticleCMD;
import org.zaksen.ecmd.command.SinParticle;

public final class ECMD extends JavaPlugin {

    public static ECMD instance;

    public static ECMD getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getCommand("partl").setExecutor(new ParticleCMD());
        getCommand("partl_sin").setExecutor(new SinParticle());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
