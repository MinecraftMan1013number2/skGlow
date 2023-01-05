package com.minecraftman.skglow.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Glow Color")
@Description("Get or delete a player's current or last glow a color. Last glow color cannot be set.")
@Examples({"make player glow red glow", "make all players glow blue glow"})
@Since("1.0.0")
@RequiredPlugins("eGlow")
public class ExprGlowColor extends SimpleExpression<EGlowColor> {
	
	// TODO:
	//  test
	
	static {
		Skript.registerExpression(ExprGlowColor.class, EGlowColor.class, ExpressionType.PROPERTY,
			"[(current|1¦last)] glow color of %player%"
		);
	}
	
	private Expression<Player> glowingPlayer;
	private int currentOrLast;
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		currentOrLast = parseResult.mark;
		glowingPlayer = (Expression<Player>) exprs[0];
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	protected @Nullable EGlowColor[] get(Event e) {
		Player player = glowingPlayer.getSingle(e);
		if (player != null) {
			if (currentOrLast == 0) {
				return new EGlowColor[]{EGlowColor.valueOf(api.getGlowColor(api.getEGlowPlayer(player)))};
			} else if (currentOrLast == 1) {
				return new EGlowColor[]{EGlowColor.valueOf(api.getEGlowPlayer(player).getLastGlow())};
			}
			return null;
		}
		return null;
	}
	
	@Override
	public boolean isSingle() {
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public Class<? extends EGlowColor> getReturnType() {
		return EGlowColor.class;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public @Nullable Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
			return CollectionUtils.array(EGlowColor.class);
		}
		return null;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		Player player = glowingPlayer.getSingle(e);
		if (player == null) return;
		switch (mode) {
			case SET:
				api.enableGlow(api.getEGlowPlayer(player), (EGlowColor) delta[0]);
				return;
			case DELETE:
				api.disableGlow(api.getEGlowPlayer(player));
		}
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return ((currentOrLast == 0) ? "Current" : "Last") + " glow color of player " + glowingPlayer.getSingle(e);
	}
}
