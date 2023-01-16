package com.minecraftman.skglow.skript.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;

public class TypeGlowColor {
	static {
		EnumUtils<EGlowColor> EGLOW_COLORS_ENUM = new EnumUtils<>(EGlowColor.class, "glow colors");
		Classes.registerClass(new ClassInfo<>(EGlowColor.class, "glowcolor")
			.user("glow ?colors?")
			.name("Glow Color")
			.description("Represents a glow color that players can glow")
			.usage(EGLOW_COLORS_ENUM.getAllNames())
			.examples("")
			.since("2.0.0")
//			.defaultExpression(new EventValueExpression<>(EGlowColor.class))
			.parser(new Parser<EGlowColor>() {
				@SuppressWarnings("NullableProblems")
				@Override
				public EGlowColor parse(String input, ParseContext context) {
					input = input.replace(" glow", "").replace("glow ", "");
					try {
//						return EGLOW_COLORS_ENUM.parse(input.toUpperCase());
						return EGlowColor.valueOf(input.toUpperCase());
					} catch (IllegalArgumentException e) {
//						Skript.error("Color '" + input + "' is invalid!");
						return null;
					}
				}
				
				@SuppressWarnings("NullableProblems")
				@Override
				public String toVariableNameString(EGlowColor color) {
					return color.name().toLowerCase() + " glowcolor";
				}
				
				@SuppressWarnings("NullableProblems")
				@Override
				public String toString(EGlowColor color, int flags) {
					return toVariableNameString(color);
				}
			})
		);
		
		Classes.registerClass(new ClassInfo<>(EffectSpeed.class, "effectspeed")
			.user("effect ?speeds?")
			.name("Effect Speed")
			.description("Represents an effect speed (fast or slow)")
			.usage("fast", "slow")
			.examples("")
			.since("2.0.0")
			.parser(new Parser<EffectSpeed>() {
				@SuppressWarnings("NullableProblems")
				@Override
				public EffectSpeed parse(String input, ParseContext context) {
					input = input.toUpperCase();
					if (!input.equals("FAST") && !input.equals("SLOW")) {
//						Skript.error("Invalid speed (" + input + ")! (Expected 'fast' or 'slow')");
						return null;
					} else {
						return EffectSpeed.valueOf(input);
					}
				}
				
				@SuppressWarnings("NullableProblems")
				@Override
				public String toVariableNameString(EffectSpeed speed) {
					return speed.name().toLowerCase();
				}
				
				@SuppressWarnings("NullableProblems")
				@Override
				public String toString(EffectSpeed speed, int flags) {
					return toVariableNameString(speed);
				}
			})
		);
	}
}
