package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.CompressedChestMenu;
import io.github.aspshijiu.avaritia26.inventory.EndCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.ExtremeSmithingMenu;
import io.github.aspshijiu.avaritia26.inventory.InfinityChestMenu;
import io.github.aspshijiu.avaritia26.inventory.NetherCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCollectorMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import io.github.aspshijiu.avaritia26.inventory.SculkCraftingMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModMenus {
	public static final ExtendedMenuType<CompressedChestMenu, BlockPos> COMPRESSED_CHEST = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("compressed_chest"),
			new ExtendedMenuType<>(CompressedChestMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<NeutronCollectorMenu, BlockPos> NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("neutron_collector"),
			new ExtendedMenuType<>(NeutronCollectorMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<NeutronCompressorMenu, BlockPos> NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("neutron_compressor"),
			new ExtendedMenuType<>(NeutronCompressorMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<ExtremeCraftingMenu, BlockPos> EXTREME_CRAFTING = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("extreme_crafting"),
			new ExtendedMenuType<>(ExtremeCraftingMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<SculkCraftingMenu, BlockPos> SCULK_CRAFTING = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("sculk_crafting"),
			new ExtendedMenuType<>(SculkCraftingMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<NetherCraftingMenu, BlockPos> NETHER_CRAFTING = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("nether_crafting"),
			new ExtendedMenuType<>(NetherCraftingMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<EndCraftingMenu, BlockPos> END_CRAFTING = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("end_crafting"),
			new ExtendedMenuType<>(EndCraftingMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<ExtremeSmithingMenu, BlockPos> EXTREME_SMITHING = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("extreme_smithing"),
			new ExtendedMenuType<>(ExtremeSmithingMenu::new, BlockPos.STREAM_CODEC)
	);
	public static final ExtendedMenuType<InfinityChestMenu, BlockPos> INFINITY_CHEST = Registry.register(
			BuiltInRegistries.MENU,
			Avaritia26.id("infinity_chest"),
			new ExtendedMenuType<>(InfinityChestMenu::new, BlockPos.STREAM_CODEC)
	);

	private ModMenus() {
	}

	public static void initialize() {
	}
}
