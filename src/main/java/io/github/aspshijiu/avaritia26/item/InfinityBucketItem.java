package io.github.aspshijiu.avaritia26.item;

import java.text.NumberFormat;
import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.component.InfinityBucketContents;
import io.github.aspshijiu.avaritia26.component.InfinityBucketContents.Change;
import io.github.aspshijiu.avaritia26.component.InfinityBucketContents.Entry;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public final class InfinityBucketItem extends Item {
	public InfinityBucketItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			if (!level.isClientSide()) {
				setContents(stack, getContents(stack).rotate());
			}
			return getContents(stack).entries().isEmpty() ? InteractionResult.FAIL : InteractionResult.SUCCESS;
		}
		BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (hit.getType() != HitResult.Type.BLOCK) {
			return InteractionResult.PASS;
		}
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		BlockPos hitPos = hit.getBlockPos();
		BlockState state = level.getBlockState(hitPos);
		if (level.mayInteract(player, hitPos) && pickUp(level, player, stack, hitPos, state)) {
			return InteractionResult.SUCCESS_SERVER;
		}
		BlockPos placePos = hitPos.relative(hit.getDirection());
		if (level.mayInteract(player, placePos) && place(level, player, stack, placePos, hit)) {
			return InteractionResult.SUCCESS_SERVER;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		if (slot != EquipmentSlot.MAINHAND || !(entity instanceof ServerPlayer player)
				|| player.connection == null || level.getGameTime() % 20 != 0) {
			return;
		}
		Entry selected = getContents(stack).selected();
		if (selected == null) {
			player.sendSystemMessage(Component.empty(), true);
			return;
		}
		player.sendSystemMessage(Component.translatable(
				"tooltip.avaritia26.infinity_bucket.message",
				FluidVariantAttributes.getName(selected.fluid()),
				formatBuckets(selected.amount())
		), true);
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable("tooltip.avaritia26.infinity_bucket.desc")
				.withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
		for (Entry entry : getContents(stack).entries()) {
			tooltip.accept(FluidVariantAttributes.getName(entry.fluid()).copy()
					.append(Component.literal(": " + formatBuckets(entry.amount()) + " mB")
							.withStyle(ChatFormatting.GRAY)));
		}
	}

	public static InfinityBucketContents getContents(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_BUCKET_CONTENTS, InfinityBucketContents.EMPTY);
	}

	public static long fill(ItemStack stack, FluidVariant fluid, long amount) {
		Change change = getContents(stack).fill(fluid, amount);
		if (change.amount() > 0) {
			setContents(stack, change.contents());
		}
		return change.amount();
	}

	public static long drainSelected(ItemStack stack, FluidVariant fluid, long amount) {
		Change change = getContents(stack).drainSelected(fluid, amount);
		if (change.amount() > 0) {
			setContents(stack, change.contents());
		}
		return change.amount();
	}

	private static boolean pickUp(
			Level level,
			Player player,
			ItemStack stack,
			BlockPos pos,
			BlockState state
	) {
		if (!(state.getBlock() instanceof BucketPickup pickup)) {
			return false;
		}
		Fluid fluid = state.getFluidState().getType();
		if (fluid == Fluids.EMPTY || getContents(stack).amount(FluidVariant.of(fluid))
				> InfinityBucketContents.CAPACITY_PER_FLUID - FluidConstants.BUCKET) {
			return false;
		}
		ItemStack pickedUp = pickup.pickupBlock(player, level, pos, state);
		if (!(pickedUp.getItem() instanceof BucketItem bucket) || bucket.getContent() == Fluids.EMPTY) {
			return false;
		}
		return fill(stack, FluidVariant.of(bucket.getContent()), FluidConstants.BUCKET) == FluidConstants.BUCKET;
	}

	private static boolean place(
			Level level,
			Player player,
			ItemStack stack,
			BlockPos pos,
			BlockHitResult hit
	) {
		Entry selected = getContents(stack).selected();
		if (selected == null || selected.amount() < FluidConstants.BUCKET) {
			return false;
		}
		Item bucketItem = selected.fluid().getFluid().getBucket();
		if (!(bucketItem instanceof BucketItem bucket) || !bucket.emptyContents(player, level, pos, hit)) {
			return false;
		}
		return drainSelected(stack, selected.fluid(), FluidConstants.BUCKET) == FluidConstants.BUCKET;
	}

	private static void setContents(ItemStack stack, InfinityBucketContents contents) {
		if (contents.entries().isEmpty()) {
			stack.remove(ModDataComponents.INFINITY_BUCKET_CONTENTS);
		} else {
			stack.set(ModDataComponents.INFINITY_BUCKET_CONTENTS, contents);
		}
	}

	private static String formatBuckets(long amount) {
		return NumberFormat.getIntegerInstance().format(amount * 1000L / FluidConstants.BUCKET);
	}
}
