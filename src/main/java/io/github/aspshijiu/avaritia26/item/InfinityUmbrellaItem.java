package io.github.aspshijiu.avaritia26.item;

import java.util.List;

import io.github.aspshijiu.avaritia26.entity.UmbrellaProjectileEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.WeatherData;

public final class InfinityUmbrellaItem extends Item {
	public static final int NORMAL = 0;
	public static final int SUN = 1;
	public static final int RAIN = 2;
	public static final int STORM = 3;
	private static final int MODE_COUNT = 4;
	private static final int MIN_DURATION = 10 * 60 * 20;
	private static final int MAX_DURATION = 20 * 60 * 20;
	private static final List<String> MODE_KEYS = List.of("normal", "sun", "rain", "storm");

	public InfinityUmbrellaItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			int nextMode = (getMode(stack) + 1) % MODE_COUNT;
			setMode(stack, nextMode);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(
						"tooltip.avaritia26.infinity_umbrella." + MODE_KEYS.get(nextMode)
				), true);
			}
			player.swing(hand, true);
			return InteractionResult.SUCCESS;
		}
		if (level instanceof ServerLevel serverLevel) {
			activate(serverLevel, player, getMode(stack));
			player.swing(hand, true);
		}
		return InteractionResult.PASS;
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		if (!(entity instanceof Player player)) {
			return;
		}
		if (player.getMainHandItem() == stack || player.getOffhandItem() == stack) {
			MobEffectInstance effect = new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, false, false);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection == null) {
				player.getActiveEffectsMap().put(MobEffects.SLOW_FALLING, effect);
			} else {
				player.addEffect(effect);
			}
		} else {
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection == null) {
				player.getActiveEffectsMap().remove(MobEffects.SLOW_FALLING);
			} else {
				player.removeEffect(MobEffects.SLOW_FALLING);
			}
		}
	}

	public static int getMode(ItemStack stack) {
		CustomModelData data = stack.getOrDefault(DataComponents.CUSTOM_MODEL_DATA, CustomModelData.EMPTY);
		Float mode = data.getFloat(0);
		if (mode == null) {
			return NORMAL;
		}
		return Math.max(NORMAL, Math.min(STORM, Math.round(mode)));
	}

	public static void setMode(ItemStack stack, int mode) {
		int safeMode = Math.max(NORMAL, Math.min(STORM, mode));
		stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(
				List.of((float) safeMode),
				List.of(),
				List.of(),
				List.of()
		));
	}

	private static void activate(ServerLevel level, Player player, int mode) {
		if (mode == NORMAL) {
			return;
		}
		if (player.getXRot() <= -85.0F) {
			setWeather(level, mode);
			return;
		}

		UmbrellaProjectileEntity projectile = new UmbrellaProjectileEntity(
				level,
				player,
				switch (mode) {
					case SUN -> UmbrellaProjectileEntity.Mode.SUN;
					case RAIN -> UmbrellaProjectileEntity.Mode.RAIN;
					default -> UmbrellaProjectileEntity.Mode.STORM;
				}
		);
		projectile.setPos(player.getX(), player.getEyeY() + 0.1, player.getZ());
		projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
		level.addFreshEntity(projectile);
		level.playSound(null, player.blockPosition(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 1.0F);
	}

	private static void setWeather(ServerLevel level, int mode) {
		WeatherData weather = level.getWeatherData();
		if (mode == SUN) {
			weather.setRaining(false);
			weather.setThundering(false);
			return;
		}
		int duration = MIN_DURATION + level.getRandom().nextInt(MAX_DURATION - MIN_DURATION + 1);
		weather.setClearWeatherTime(0);
		weather.setRainTime(duration);
		weather.setThunderTime(duration);
		weather.setRaining(true);
		weather.setThundering(mode == STORM);
	}
}
