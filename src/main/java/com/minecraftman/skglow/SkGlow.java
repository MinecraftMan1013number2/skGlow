package com.minecraftman.skglow;

import ch.njol.skript.SkriptAddon;
import com.minecraftman.skglow.utils.AddonLoader;
import com.minecraftman.skglow.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
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
		
		registerMetrics();
		
		logger.info("skGlow has been enabled!");
		
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	public static Logger getPluginLogger() {
		return logger;
	}
	
	private void registerMetrics() {
		Metrics metrics = new Metrics(this, 12725);

		String version;
		Plugin skriptPlugin = Bukkit.getServer().getPluginManager().getPlugin("Skript");
		if (skriptPlugin != null) {
			version = skriptPlugin.getDescription().getVersion();
		} else {
			version = "Unknown";
		}
		metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> version));
	}
}
