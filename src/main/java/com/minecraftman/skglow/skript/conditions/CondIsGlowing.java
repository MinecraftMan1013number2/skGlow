package com.minecraftman.skglow.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Is Glowing")
@Description("See if a player is glowing")
@Examples({"if player is glowing:", "if player is not glowing:"})
@Since("2.0.1")
@RequiredPlugins("eGlow")
public class CondIsGlowing extends Condition {
	private Expression<Player> player;
	private final EGlowAPI api = EGlow.getAPI();
	
	static {
		Skript.registerCondition(CondIsGlowing.class,
				"%player% is glowing",
				"%player% (isn't|is not) glowing"
		);
	}
	
	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) exprs[0];
		setNegated(matchedPattern == 1);
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public boolean check(Event e) {
		Player p = player.getSingle(e);
		assert p != null;
		boolean glowing = !(api.getGlowColor(api.getEGlowPlayer(player.getSingle(e))).equals(""));
		if (isNegated()) {
			return !glowing;
		}
		return glowing;
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return player.getSingle(e) + " is glowing";
	}
}