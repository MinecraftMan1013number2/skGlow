package com.minecraftman.skglow.skript.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprGlowColor extends SimpleExpression<EGlowColor> {
	private Expression<EGlowColor> glowColor;
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		glowColor = (Expression<EGlowColor>) exprs[0];
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	protected @Nullable EGlowColor[] get(Event e) {
		return new EGlowColor[0];
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
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Glow color of player: " + glowColor.getSingle(e);
	}
}
