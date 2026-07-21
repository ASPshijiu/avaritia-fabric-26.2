package io.github.aspshijiu.avaritia26.transfer;

import java.util.Iterator;

import io.github.aspshijiu.avaritia26.component.InfinityBucketContents;
import io.github.aspshijiu.avaritia26.item.InfinityBucketItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;

public final class InfinityBucketStorage implements Storage<FluidVariant> {
	private final ContainerItemContext context;

	private InfinityBucketStorage(ContainerItemContext context) {
		this.context = context;
	}

	public static void initialize() {
		FluidStorage.ITEM.registerForItems((stack, context) -> new InfinityBucketStorage(context),
				ModItems.INFINITY_BUCKET);
	}

	@Override
	public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		ItemStack updated = stack();
		if (!updated.is(ModItems.INFINITY_BUCKET)) {
			return 0;
		}
		long inserted = InfinityBucketItem.fill(updated, resource, maxAmount);
		return inserted > 0 && exchange(updated, transaction) ? inserted : 0;
	}

	@Override
	public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notBlankNotNegative(resource, maxAmount);
		ItemStack updated = stack();
		if (!updated.is(ModItems.INFINITY_BUCKET)) {
			return 0;
		}
		long extracted = InfinityBucketItem.drainSelected(updated, resource, maxAmount);
		return extracted > 0 && exchange(updated, transaction) ? extracted : 0;
	}

	@Override
	public Iterator<StorageView<FluidVariant>> iterator() {
		return InfinityBucketItem.getContents(stack()).entries().stream()
				.<StorageView<FluidVariant>>map(entry -> new View(entry.fluid()))
				.iterator();
	}

	private ItemStack stack() {
		return context.getItemVariant().toStack();
	}

	private boolean exchange(ItemStack stack, TransactionContext transaction) {
		return context.exchange(ItemVariant.of(stack), 1, transaction) == 1;
	}

	private final class View implements StorageView<FluidVariant> {
		private final FluidVariant fluid;

		private View(FluidVariant fluid) {
			this.fluid = fluid;
		}

		@Override
		public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
			return InfinityBucketStorage.this.extract(resource, maxAmount, transaction);
		}

		@Override
		public boolean isResourceBlank() {
			return getAmount() == 0;
		}

		@Override
		public FluidVariant getResource() {
			return fluid;
		}

		@Override
		public long getAmount() {
			return InfinityBucketItem.getContents(stack()).amount(fluid);
		}

		@Override
		public long getCapacity() {
			return InfinityBucketContents.CAPACITY_PER_FLUID;
		}
	}
}
