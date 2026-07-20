package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class InfinityPickaxeItem extends Item {
	public InfinityPickaxeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide()) {
			var fortune = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
			if (EnchantmentHelper.getItemEnchantmentLevel(fortune, stack) < 10) {
				stack.enchant(fortune, 10);
			}
			stack.set(ModDataComponents.INFINITY_PICKAXE_HAMMER, !isHammer(stack));
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (isHammer(stack)) {
			double yaw = Math.toRadians(attacker.getYRot());
			target.push(-Math.sin(yaw) * 5.0, 2.0, Math.cos(yaw) * 5.0);
		}
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return isHammer(stack) ? 5.0F : 9999.0F;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_PICKAXE)
				|| state.is(BlockTags.MINEABLE_WITH_SHOVEL)
				|| state.is(BlockTags.IMPERMEABLE)
				|| state.is(BlockTags.ICE);
	}

	public static boolean isHammer(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_PICKAXE_HAMMER, false);
	}
}
