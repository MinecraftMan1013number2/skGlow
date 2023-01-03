package com.minecraftman.skglow.skript.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprGlowColor extends SimpleExpression {
	@Nullable
	@Override
	protected Object[] get(Event e) {
		return new Object[0];
	}
	
	@Override
	public boolean isSingle() {
		return false;
	}
	
	@Override
	public Class getReturnType() {
		return null;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return null;
	}
	
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		return false;
	}
}
