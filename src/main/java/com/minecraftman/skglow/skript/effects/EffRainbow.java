package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.minecraftman.skglow.skript.types.EffectSpeed;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowEffect;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Rainbow Glow")
@Description("Make a player glow in a rainbow")
@Examples({"make all players glow rainbow slowly", "make player glow rainbow fast"})
@Since("2.0.0")
@RequiredPlugins("eGlow")
public class EffRainbow extends Effect {
	static {
		Skript.registerEffect(EffRainbow.class,
			"make %players% glow rainbow %effectspeed%[ly]"
		);
	}
	
	private Expression<Player> players;
	private Expression<EffectSpeed> effectSpeed;
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		players = (Expression<Player>) exprs[0];
		effectSpeed = (Expression<EffectSpeed>) exprs[1];
		return true;
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	protected void execute(Event e) {
		EGlowEffect effect = EGlowEffect.valueOf("RAINBOW_" + effectSpeed.getSingle(e).name().toUpperCase());
		for (Player player : players.getArray(e)) {
			api.enableGlow(api.getEGlowPlayer(player), effect);
		}
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		String speed = effectSpeed.toString(e, debug).toLowerCase();
		return "make " + players.toString(e, debug) + " glow rainbow " + speed + (speed.equals("slow") ? "ly" : "");
	}
}
