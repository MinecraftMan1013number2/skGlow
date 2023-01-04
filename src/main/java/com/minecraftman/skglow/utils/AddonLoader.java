package com.minecraftman.skglow.utils;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.minecraftman.skglow.SkGlow;

import java.io.IOException;

public class AddonLoader {
	
	SkGlow main;
	private SkriptAddon addon;
	public AddonLoader(SkGlow main) {
		this.main = main;
	}
	
	public SkriptAddon register() {
		addon = Skript.registerAddon(main);
		main.getLogger().info("Registered addon! Loading elements...");
		return addon;
	}
	
	public void loadConditions() {
		try {
			addon.loadClasses("com.minecraftman.skglow.skript.conditions");
			main.getLogger().info("-> Loaded conditions");
		} catch (IOException e) {
			main.getLogger().severe("Something went horribly wrong loading skGlow's conditions!");
			e.printStackTrace();
		}
	}
	
	public void loadEffects() {
		try {
			addon.loadClasses("com.minecraftman.skglow.skript.effects");
			main.getLogger().info("-> Loaded effects");
		} catch (IOException e) {
			main.getLogger().severe("Something went horribly wrong loading skGlow's effects!");
			e.printStackTrace();
		}
	}
	
	public void loadExpressions() {
		try {
			addon.loadClasses("com.minecraftman.skglow.skript.expressions");
			main.getLogger().info("-> Loaded expressions");
		} catch (IOException e) {
			main.getLogger().severe("Something went horribly wrong loading skGlow's expressions!");
			e.printStackTrace();
		}
	}
	
	public void loadTypes() {
		try {
			addon.loadClasses("com.minecraftman.skglow.skript.types");
			main.getLogger().info("-> Loaded types");
		} catch (IOException e) {
			main.getLogger().severe("Something went horribly wrong loading skGlow's types!");
			e.printStackTrace();
		}
	}
}
