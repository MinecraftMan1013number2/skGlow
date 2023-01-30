package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.minecraftman.skglow.skript.types.EffectSpeed;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowBlink;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Make Blink")
@Description("Make a player blink a color")
@Examples({"make player blink purple slowly", "apply slow red blink to player"})
@Since("2.0.0")
@RequiredPlugins("eGlow")
public class EffBlink extends Effect {
	static {
		Skript.registerEffect(EffBlink.class,
				"make %players% blink %glowcolor% with speed %effectspeed%",
				"apply %glowcolor% blink with speed %effectspeed% to %players%"
		);
	}
	
	Expression<Player> players;
	Expression<EffectSpeed> speed;
	Expression<EGlowColor> glowColor;
	
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		if (matchedPattern == 0) {
			players = (Expression<Player>) exprs[0];
			speed = (Expression<EffectSpeed>) exprs[2];
		} else {
			speed = (Expression<EffectSpeed>) exprs[0];
			players = (Expression<Player>) exprs[2];
		}
		glowColor = (Expression<EGlowColor>) exprs[1];
		return true;
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	protected void execute(Event e) {
		String effectSpeed = speed.getSingle(e).name().toUpperCase();
		String blinkColor = glowColor.getSingle(e).name().toUpperCase();
		EGlowBlink value = EGlowBlink.valueOf(blinkColor + "_" + effectSpeed);
		for (Player player : players.getArray(e)) {
			api.enableGlow(api.getEGlowPlayer(player), value);
		}
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		String s = speed.toString(e, debug);
		return "make " + players.toString(e, debug) + " blink " + glowColor.toString(e, debug) + " " + s + (s.equals("slow") ? "ly" : "");
	}
}