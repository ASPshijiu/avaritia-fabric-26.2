package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModMenus {
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

	private ModMenus() {
	}

	public static void initialize() {
	}
}
