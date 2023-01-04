package com.minecraftman.skglow;

import ch.njol.skript.SkriptAddon;
import com.minecraftman.skglow.utils.AddonLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SkGlow extends JavaPlugin {
	private static Logger logger;
	
	SkriptAddon addon;
	
	@Override
	public void onEnable() {
		logger = getLogger();
		
		AddonLoader loader = new AddonLoader(this);
		addon = loader.register();
		loader.loadConditions();
		loader.loadEffects();
		loader.loadExpressions();
		loader.loadTypes();
		
//		registerMetrics();
		
		logger.info("skGlow has been enabled!");
		
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
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
