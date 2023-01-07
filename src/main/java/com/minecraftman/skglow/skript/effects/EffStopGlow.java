package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Stop Glow")
@Description("Stop a player from glowing")
@Examples({"make all players stop glowing"})
@Since("1.0.0")
@RequiredPlugins("eGlow")
public class EffStopGlow extends Effect {
	
	static {
		Skript.registerEffect(EffStopGlow.class,
			"make %players% stop glowing",
				"disable glowing for %players%"
		);
	}
	
	private final EGlowAPI api = EGlow.getAPI();
	private Expression<Player> players;
	
	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		this.players = (Expression<Player>) exprs[0];
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	protected void execute(Event e) {
		for (Player player : players.getArray(e)) {
			api.disableGlow(api.getEGlowPlayer(player));
		}
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "make " + players.toString(e, debug) + " stop glowing";
	}
}
