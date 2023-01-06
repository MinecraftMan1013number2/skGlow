package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.minecraftman.skglow.SkGlow;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.MrGraycat.eGlow.EGlow;
import me.MrGraycat.eGlow.Manager.Interface.IEGlowPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Make Glow")
@Description("Make a player glow a color")
@Examples({"make player glow red glow", "make all players glow blue glow"})
@Since("1.0.0")
@RequiredPlugins("eGlow")
public class EffMakeGlow extends Effect {
	
	static {
		// TODO:
		//  possibly make
		Skript.registerEffect(EffMakeGlow.class,
			"make %players% [glow] %glowcolor%",
			"apply %glowcolor% to %players%"
		);
	}
	
	private Expression<Player> players;
	private Expression<EGlowColor> color;
//	private Expression<SkriptColor> color;
	private final EGlowAPI api = EGlow.getAPI();
	
	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		this.players = (Expression<Player>) exprs[0];
		this.color = (Expression<EGlowColor>) exprs[1];
		
		if (matchedPattern == 0) {
			Skript.error("This syntax is depricated! Please change your syntax to 'apply %glowcolor% to %players%'!");
		}
		
		return true;
	}
	
	@SuppressWarnings("NullableProblems")
	@Override
	protected void execute(Event e) {
//		EGlowColor glowColor = ColorTypes.toEGlowColor(color.getSingle(e));
		EGlowColor glowColor = color.getSingle(e);
		if (glowColor == null) {
			SkGlow.getPluginLogger().info("Color '" + color.getSingle(e) + "' is invalid!");
			return;
		}
		for (Player player : players.getArray(e)) {
			IEGlowPlayer ieGlowPlayer = api.getEGlowPlayer(player);
			api.disableGlow(ieGlowPlayer);
			api.enableGlow(ieGlowPlayer, glowColor);
		}
	}
	
	@SuppressWarnings({"NullableProblems", "DataFlowIssue"})
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "make " + players.toString(e, debug) + " glow " + ((color != null) ? color.getSingle(e) : "white");
	}
	
	
}