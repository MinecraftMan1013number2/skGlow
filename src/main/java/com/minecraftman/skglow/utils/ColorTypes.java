package com.minecraftman.skglow.utils;

import ch.njol.skript.util.SkriptColor;
import me.MrGraycat.eGlow.API.Enum.EGlowBlink;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;

public class ColorTypes {
	public static EGlowColor toEGlowColor(SkriptColor original) {
		try {
			return EGlowColor.valueOf(original.getName().toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public static EGlowBlink toEGlowBlink(SkriptColor original, boolean blinkFast) {
		String blinkSpeed = blinkFast ? "FAST" : "SLOW";
		return EGlowBlink.valueOf(original.getName().toUpperCase() + "_" + blinkSpeed);
		
	}
}
