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
					input = input.replace(" glow", "");
					try {
						return EGlowColor.valueOf(input.toUpperCase());
					} catch (IllegalArgumentException e) {
						return null;
					}
//					return EGLOW_COLORS_ENUM.parse(input);
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
			/*
			.serializer(new Serializer<EGlowColor>() {
				@Override
				public Fields serialize(EGlowColor color) throws NotSerializableException {
					Fields fields = new Fields();
					fields.putObject("color", color);
					return fields;
				}
				
				@Override
				public void deserialize(EGlowColor color, Fields fields) throws StreamCorruptedException, NotSerializableException {
					fields.getAndRemoveObject("color", EGlowColor.class);
				}
				
				@Override
				public boolean mustSyncDeserialization() {
					return false;
				}
				
				@Override
				protected boolean canBeInstantiated() {
					return false;
				}
			})
			 */
		);
	}
}
