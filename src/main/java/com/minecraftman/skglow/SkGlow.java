package com.minecraftman.skglow;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class SkGlow extends JavaPlugin {
	
	private static Logger logger;
	private static SkGlow instance;
	
	@Override
	public void onEnable() {
		logger = getLogger();
		SkriptAddon addon = Skript.registerAddon(this);
		instance = this;
		
		try {
			addon.loadClasses("com.minecraftman.skglow.skript", "effects", "conditions");
		} catch (IOException ex) {
			logger.severe("Something went horribly wrong loading skGlow's addon classes!");
			ex.printStackTrace();
		}
		
//		registerMetrics();
		
		logger.info("skGlow has been enabled!");
		
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	public static Plugin getInstance() {
		return instance;
	}
	
	public static Logger getPluginLogger() {
		return logger;
	}
	
//	private void registerMetrics() { // 12725
//		Metrics metrics = new Metrics(this, 12725);
//
//		String version = "Unknown";
//		Plugin skriptPlugin = Bukkit.getServer().getPluginManager().getPlugin("Skript");
//		if (skriptPlugin != null) {
//			version = skriptPlugin.getDescription().getVersion();
//		}
//		String finalVersion = version;
//		metrics.addCustomChart(new SimplePie("skript_version", () -> finalVersion));
//	}
}
