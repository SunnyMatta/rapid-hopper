package com.sunnymatta.rapidhopper.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.sunnymatta.rapidhopper.RapidHopper;

@Mixin(HopperBlockEntity.class)
public abstract class hopperspeed extends BlockEntity {

	@Shadow
	private int transferCooldown;	

	protected hopperspeed(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}
	
	@Inject(method = "setTransferCooldown", at = @At("HEAD"), cancellable = true)
	private void onSetTransferCooldown(int cooldown, CallbackInfo info) {
		World world = this.getWorld();
		if (world instanceof net.minecraft.server.world.ServerWorld server) {
			if (cooldown > 0 && !server.isClient()) {
				int customCooldown = server.getGameRules().getValue(RapidHopper.customHopperSpeed);

				this.transferCooldown = Math.max(0, customCooldown);
				info.cancel();
			}
		}
	}

}