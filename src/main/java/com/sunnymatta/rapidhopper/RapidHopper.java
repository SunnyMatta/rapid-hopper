package com.sunnymatta.rapidhopper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;

public class RapidHopper implements ModInitializer {
	public static final String MOD_ID = "rapid-hopper";

	public static GameRule<Integer> customHopperSpeed = GameRuleBuilder
				.forInteger(8)
				.category(GameRuleCategory.MISC)
				.buildAndRegister(Identifier.of("minecraft", "hopper_speed"));;

	@Override
	public void onInitialize() {
		
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
