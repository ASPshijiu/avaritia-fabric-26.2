package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class CrystalPickaxeItem extends Item {
	public CrystalPickaxeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide()) {
			var silkTouch = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
					.getOrThrow(Enchantments.SILK_TOUCH);
			boolean enabled = EnchantmentHelper.getItemEnchantmentLevel(silkTouch, stack) == 0;
			if (enabled) {
				stack.enchant(silkTouch, 1);
			} else {
				EnchantmentHelper.updateEnchantments(stack,
						mutable -> mutable.removeIf(enchantment -> enchantment.is(Enchantments.SILK_TOUCH)));
			}
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(enabled
						? "tooltip.avaritia26.crystal_pickaxe.silk_touch_enabled"
						: "tooltip.avaritia26.crystal_pickaxe.silk_touch_disabled"), true);
			}
		}
		player.swing(hand);
		return InteractionResult.SUCCESS;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return 100.0F;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_PICKAXE) || canBreakUnbreakable(state);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable("tooltip.avaritia26.crystal_pickaxe.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
	}

	public static boolean canBreakUnbreakable(BlockState state) {
		return state.is(Blocks.BEDROCK)
				|| state.is(Blocks.END_PORTAL_FRAME)
				|| state.is(Blocks.END_PORTAL)
				|| state.is(Blocks.REINFORCED_DEEPSLATE);
	}
}
