package io.github.aspshijiu.avaritia26.event;

import io.github.aspshijiu.avaritia26.component.ClockAccelerationData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public final class ModClockEvents {
	private ModClockEvents() {
	}

	public static void initialize() {
		ServerTickEvents.END_SERVER_TICK.register(server ->
				server.getAllLevels().forEach(ModClockEvents::tickAcceleratedBlocks));
	}

	public static void tickAcceleratedBlocks(ServerLevel level) {
		ClockAccelerationData data = level.getDataStorage().computeIfAbsent(ClockAccelerationData.TYPE);
		data.entries().forEach((pos, multiplier) -> tickAcceleratedBlock(level, data, pos, multiplier));
	}

	private static void tickAcceleratedBlock(
			ServerLevel level,
			ClockAccelerationData data,
			BlockPos pos,
			int multiplier
	) {
		if (!level.isLoaded(pos)) {
			return;
		}
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof BonemealableBlock growable) {
			for (int tick = 0; tick < multiplier; tick++) {
				state = level.getBlockState(pos);
				if (!growable.isValidBonemealTarget(level, pos, state)) {
					break;
				}
				if (growable.isBonemealSuccess(level, level.getRandom(), pos, state)) {
					growable.performBonemeal(level, level.getRandom(), pos, state);
				}
			}
			particles(level, pos, multiplier);
			return;
		}

		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity == null || !(state.getBlock() instanceof EntityBlock entityBlock)) {
			data.remove(pos);
			return;
		}
		tickBlockEntity(level, pos, state, entityBlock, blockEntity, multiplier);
		particles(level, pos, multiplier);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private static void tickBlockEntity(
			ServerLevel level,
			BlockPos pos,
			BlockState state,
			EntityBlock block,
			BlockEntity blockEntity,
			int multiplier
	) {
		BlockEntityTicker ticker = block.getTicker(level, state, blockEntity.getType());
		if (ticker == null) {
			return;
		}
		for (int tick = 0; tick < multiplier && !blockEntity.isRemoved(); tick++) {
			ticker.tick(level, pos, state, blockEntity);
		}
		blockEntity.setChanged();
	}

	private static void particles(ServerLevel level, BlockPos pos, int multiplier) {
		if (level.getGameTime() % 5 != 0) {
			return;
		}
		level.sendParticles(
				ParticleTypes.ENCHANT,
				pos.getX() + 0.5,
				pos.getY() + 0.5,
				pos.getZ() + 0.5,
				Math.min(10, multiplier),
				0.6,
				0.6,
				0.6,
				0.0
		);
	}
}
