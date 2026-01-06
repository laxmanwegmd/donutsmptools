package xyz.laxmanwe.laxstools;

import org.bukkit.plugin.java.JavaPlugin;

public final class Laxstools extends JavaPlugin {

    @Override
    public void onEnable() {
        LaxCommands commands = new LaxCommands(this);
        getCommand("laxpick").setExecutor(commands);
        getCommand("laxbalta").setExecutor(commands);
        getCommand("laxkurek").setExecutor(commands);
        getServer().getPluginManager().registerEvents(commands, this);
        getLogger().info("Laxstools plugin aktif edildi!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Laxstools plugin devre dışı bırakıldı!");
    }
}