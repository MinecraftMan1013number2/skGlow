package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.minecraftman.skglow.skript.types.BlinkSpeed;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowBlink;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EffBlink extends Effect {
	
	// TODO:
	//  finish class
	
	static {
		Skript.registerEffect(EffBlink.class,
				"make %players% blink %glowcolor% %blinkspeed%[ly]",
				"apply %blinkspeed% %glowcolor% blink to %players%"
		);
	}
	
	Expression<Player> players;
	Expression<BlinkSpeed> speed;
	Expression<EGlowColor> glowColor;
	
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		// players{0}, glowColor{1}, speed{2}
		// speed{0}, glowColor{1}, players{2};
		if (matchedPattern == 0) {
			players = (Expression<Player>) exprs[0];
			speed = (Expression<BlinkSpeed>) exprs[2];
		} else {
			speed = (Expression<BlinkSpeed>) exprs[0];
			players = (Expression<Player>) exprs[2];
		}
		glowColor = (Expression<EGlowColor>) exprs[1];
		return true;
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	protected void execute(Event e) {
		String blinkSpeed = speed.getSingle(e).name().toUpperCase();
		String blinkColor = glowColor.getSingle(e).name().toUpperCase();
		EGlowBlink value = EGlowBlink.valueOf(blinkColor + "_" + blinkSpeed);
		for (Player player : players.getArray(e)) {
			api.enableGlow(api.getEGlowPlayer(player), value);
		}
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		String s = speed.getSingle(e).name().toLowerCase();
		return "make " + Arrays.toString(players.getArray(e)) + " blink " + glowColor.getSingle(e) + " " + s + (s.equals("slow") ? "ly" : "");
	}
}
