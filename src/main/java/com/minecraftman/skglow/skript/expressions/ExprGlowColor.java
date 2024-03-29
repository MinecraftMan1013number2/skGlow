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
import me.MrGraycat.eGlow.Manager.Interface.IEGlowPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Glow Color")
@Description("Get or delete a player's current or last glow a color. Last glow color cannot be set.")
@Examples({"make player glow red glow", "make all players glow blue glow"})
@Since("1.0.0")
@RequiredPlugins("eGlow")
public class ExprGlowColor extends SimpleExpression<EGlowColor> {
	static {
		Skript.registerExpression(ExprGlowColor.class, EGlowColor.class, ExpressionType.PROPERTY,
			"[current] glow color of %player%",
			"%player%['s] [current] glow color"
		);
	}
	
	private Expression<Player> glowingPlayer;
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"NullableProblems", "unchecked"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		glowingPlayer = (Expression<Player>) exprs[0];
		return true;
	}
	//TODO:
	// Test with colors containing and underscore (ex DARK_GREEN)

	@SuppressWarnings("NullableProblems")
	@Override
	protected @Nullable EGlowColor[] get(Event e) {
		Player player = glowingPlayer.getSingle(e);
		if (player == null || !player.isOnline()) return null;
		IEGlowPlayer ieGlowPlayer = api.getEGlowPlayer(player);
		ChatColor chatColorGlow = ieGlowPlayer.getActiveColor();
		if (chatColorGlow == null) return new EGlowColor[]{EGlowColor.NONE};
		int index = chatColorGlow.name().indexOf("_");
		String glowColor = chatColorGlow.name();
		if (index >= 0) {
			glowColor = glowColor.substring(index);
		}
		
		return new EGlowColor[]{EGlowColor.valueOf(glowColor)};
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
	
	@SuppressWarnings("NullableProblems")
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Current glow color of player '" + glowingPlayer.toString(e, debug) + "'";
	}
}
